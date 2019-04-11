/* socket\room_chat\app.js */
const app = require('express')();
const http = require('http').Server(app);
const io = require('socket.io')(http);
var redis = require("redis");
const v = require('voca');
var session = require('express-session');
var client;
var testdata;
app.use(session({
	secret: '12sdfwerwersdfserwerwef', //keboard cat (??€? κ°?)
	resave: false,
	saveUninitialized: true
}));


app.set('view engine', 'ejs');
app.set('views', './views');


let room = ['room1', 'room2'];
let a = 0;

var conn;
var oracledb = require("oracledb");
oracledb.autoCommit = true;
oracledb.getConnection({
  user:"tom",
  password:"tom",
  connectString:"39.127.7.47/orcl"},function(err,con){
    if(err){
      console.log("? ???¬",err);
    }
    conn=con;

    

});

app.get('/', (req, res) => {
  res.render('chat');
});
var sRoom;
//λͺ©λ‘μ€? ??λ₯? ?΄λ¦??????
app.get('/roomchat', (req, res) => {
  console.log("?Έ??΄ ?€?΄??Έ :", req.session)
  console.log("?Έ??΄ ?€?΄??Έ :", req.session.nickname)
  sRoom = req.query.room_id;
  if(req.session.nickname==undefined){
    res.render('tomson');
  }
  if(sRoom == undefined){
  sRoom = req.query.room_id;} // μΏΌλ¦¬?€?Έλ§? κ°μ λ°μ?¨?€.
  console.log("??₯?©??€! : "+sRoom);
  //INSERT INTO MESSAGE (MESSAGE_num, SENDER_num, ROOM_ID, CONTENT) VALUES (message_seq.NEXTVAL, (select m_num from member where nickname = '"+name+"'), "+num+", '"+msg+"')
  conn.execute('select message_num, sender_num,member.nickname, room_id, content from message, member where message.sender_num = member.m_num and room_id = '+parseInt(sRoom)+' order by message_num asc',function(err,result){
    console.log('λͺ»μ½:?'+sRoom);
  if(err){
      console.log("/ROOMCHAT : ?±λ‘μ€ ??¬κ°? λ°μ? λͺ»μ½..? ? ", err);
  }else{
    console.log("result: "+result);
    console.log("result: ",result.rows); 
    res.render('roomchat2',{result:JSON.stringify(result),nickname:req.session.nickname,roomid:sRoom });
  }
  });
});






// λ°©λͺ©λ‘? λΆλ¬?€κΈ?
//select room_id, room_title from test_room where buyer_id = 'jack' or seller_id = 'jack';
app.get('/jackchat', (req, res) => {//localhost:3000/jackchat ?Όλ‘? ? κ·Όμ ?€?
  client = redis.createClient(6379, "localhost");//localhost6379?¬?Έ? redisκ°μ²΄? ? κ·Όν?€.
    client.get("user", function(err, val) {//redisκ°μ²΄? "user"?Ό? ?€? κ°μ μ°Ύμ ?¨??€?
    testdata=val;//?΄?Ή ?€? κ°μ ?£?΄μ£Όκ³ 
    req.session.nickname = val;
    var arr = [];
    if(val === null) {//κ°μ΄ ??€λ©? ?κ±°νκ³? ?‘
      console.log('>>>>> result : null ');
    }
    else {//κ°μ΄ ??€λ©? ?€? select c.room_id, c.buyer_num, c.seller_num, c.pro_num, o.title        from chatroom c, production o where o.pro_num = c.pro_num and (      seller_num = (select m_num from member where nickname = 'jackson') or      buyer_num = (select m_num from member where nickname = 'jackson'));
    //select c.room_id, (select nickname from member where m_num = c.buyer_num), (select nickname from member where m_num = c.seller_num), c.pro_num, o.title from chatroom c, production o where o.pro_num = c.pro_num and (      seller_num = (select m_num from member where nickname = 'tom') or      buyer_num = (select m_num from member where nickname = 'tom'));
      var loglogsql = "select c.room_id, c.buyer_num, c.seller_num, c.pro_num, o.title, (select nickname from member where m_num = c.buyer_num) C_buyer_nickname, (select nickname from member where m_num = c.seller_num) C_seller_nickname  from chatroom c, production o where o.pro_num = c.pro_num and (      seller_num = (select m_num from member where nickname = '"+val+"') or      buyer_num = (select m_num from member where nickname = '"+val+"'))";
      //conn.execute("select room_id, room_title from test_room where buyer_id = '"+val+"' or seller_id = '"+val+"'",
      conn.execute(loglogsql,
      function(err, result){
        console.log("result: λ¦¬μ??΄?΄?Ό?Έ",result);
        console.log("result.rows: λ¦¬μ??΄?΄?Ό?Έλ‘μ°",result.rows);
        console.log("result? κΈΈμ΄κ°? @@@@@@@@@@   "+result.rows.length);
      //conn.execute("select room_id, room_title from test_room where buyer_id = 'tom' or seller_id = 'tom'",function(err,result){ ?΄κ²? ?€??΄?©??€.
        if(err){
            console.log("/jackchat : ?±λ‘μ€ ??¬κ°? λ°μ??΄?!! ", err);
        }else if(!result.rows.length){
            console.log("/jackchat : ROwsκ°? 0?΄?€. ");
            res.render('roomlist',{result:JSON.stringify(result),nickname:req.session.nickname}); 
        }else{
            // 
            // result? JSON Type. metaData, rows ?Ό? keyλ₯? κ°?μ§?κ³? ??€. {metaData? ?€λ£°μΌ?΄ ??΄ κΈ°μ ?μ§???}
            // [metaData:{key:value}][rows:['1301015','κΉ??¬κ·?','010-4241-1101']['1600243','λ°λ‘?‘','010-4447-2663']...]
            // result.rows? selectλ¬Έμ κ²°κ³Όμ§ν©? κ°?μ§?κ³? ??Όλ©? ?΄μ€λ°°?΄? ??λ‘? λ°ν??€.
            // result.rows       --> [['1301006' ,'κΉ??¬κ·?'], ['1500222', 'λ°λ­λ­?'], ['1711111', '??']
            // result.rows[0]    --> ['1301006', 'κΉ??¬κ·?'] ?? κ°μ? ??
            // result.rows[1][1] --> ['λ°λ­λ­?']
            // result.rows[x][y] --> x : rowλ₯? ? ?,  y:column? ? ?
            /*
            var jsonoobj = {};
            for(var i = 0; i < result.rows.length; i++){
              jsonoobj = {
                  room_id : result.rows[i][0],
                  room_title : result.rows[i][1]
              }
              console.log("JSON?Όλ‘? λ§λ¬ : ", jsonoobj);
              var realJson = JSON.parse(JSON.stringify(jsonoobj));
              var stringjack = JSON.stringify(jsonoobj);
              arr.push(realJson);
              console.log("arrλ‘? λ§λ¬ : ", arr);
            }
            console.log("JSONλ°°μ΄? μ’λ£", arr);*/
              
              res.render('roomlist',{result:JSON.stringify(result),nickname:req.session.nickname});         //  res.render('roomlist', arr);
        }//DBμΏΌλ¦¬λ¬?- if else 
      });//if else- redis? κ°μ΄ ??κ²? ???Όλ©?
      }//client.get ?¨?
    });//redis create ?¨?
});//app.get?¨?

io.on('connection', (socket) => {
  socket.on('disconnect', () => {
    console.log('user disconnected');
  });


  socket.on('leaveRoom', (num, name) => {
    socket.leave(room[num], () => {
      console.log(name + ' leave a ' + room[num]);
      io.to(room[num]).emit('leaveRoom', num, name);
    });
  });


  socket.on('joinRoom', (num, name) => {
    socket.join(room[num], () => {
      console.log(name + ' join a ' + room[num]);
      /*  //λ°©μ?₯? ??κ±΄λ° ????
      conn.execute("insert into test_user(USER_ID) values('"+name+"')",function(err,result){
        if(err){
            console.log("?±λ‘μ€ ??¬κ°? λ°μ??΄?!!", err);
           
        }else{
            console.log("result : ",result);
          
        }
      });
      */

      io.to(room[num]).emit('joinRoom', num, name);
    });
  });


  socket.on('chat message', (num, name, msg) => {
    a = num;

    console.log('?? '+name+'? λ©μμ§?? ?¬ : num :', num, msg);
    
    conn.execute("INSERT INTO MESSAGE (MESSAGE_num, SENDER_num, ROOM_ID, CONTENT) VALUES (message_seq.NEXTVAL, (select m_num from member where nickname = '"+name+"'), "+num+", '"+msg+"')",function(err,result){
      if(err){
          console.log("?±λ‘μ€ ??¬κ°? λ°μ??΄?!! λ©μμ§? ?? ₯??¬", err);
      }else{

          console.log("result : ",result);
      }
    });
    io.to(room[num]).emit('chat message', name, msg);
  });
});




http.listen(3000, () => {
  console.log('Connect at 3000');
});