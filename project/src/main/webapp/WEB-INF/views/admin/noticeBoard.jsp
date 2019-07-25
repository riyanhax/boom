<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지 입니다.</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
  integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<style>
.page-header{
margin-top:1%;
font-weight: bold !important;

}

#searchForm{ 
    margin-bottom: 0.5%;
        margin-left: 1.2%;
}
.table-striped{
margin:auto;
width:62.2% !important;
margin-bottom:1.5px !important;
}   
.panel-heading{   
display: inline-block;
vertical-align: middle;
float: right;
}
.btn-primary{
color: black !important;
border-color: black !important;
font-weight: bold !important;
background-color:#f8f8f8 !important;
}
.btn{
padding: 0.13rem 0.70rem !important;

}
.ros{
    margin-top: 2%;
    border-radius: 5px;
    border: solid 1px #ffffff;
    background-color: white;
    -webkit-box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .16), 0 2px 10px 0 rgba(0, 0, 0, .12);
    width: 62%;
    margin-left: 19% !important;
    margin-bottom: 25px;
}
.sele{

    vertical-align: middle;
    display: inline-block;
}
.keywi{
 vertical-align: middle;
     display: inline-block;
     margin-left: -0.4%;
}
  .hehe{
  height:27px;
  }  
.move_btn{
 vertical-align: middle;
     display: inline-block;
       margin-left: -2.6%;
}
.btn-default{
padding: 7.4% 20% !important;
}

.table thead th{
   background-color: black !important;
   text-align:center;
   color:#dacece !important;
   }
   
   .nu{
   text-align:center;
   }
.table-bordered td,.table-bordered th{
   border:none !important;
   text-align:center;
   }
   
   .table td,.table th{
   padding: 0.68rem !important;
   }
   
a:link { color:#606975 !important; text-decoration: none;}
a:visited { color:#606975 !important; text-decoration: none;}
a:hover { color:#606975 !important; text-decoration: none;}
a:active { color:#606975 !important; text-decoration: none;}
a:focus { color:#606975 !important; text-decoration: none;}

.page-link{
background-color:#f8f8f8 !important; 
border: none !important;
}

.pull-center{
margin-top: 0.5% !important;
font-weight: bold !important;
}
.roa{
    width: 62.6% !important;
    margin: auto !important;
}
.bbb{
 border-color: #f8f8f8 !important;
    color: black !important;
    border-radius: 5px !important;
        background-color: #f8f8f8 !important;
    -webkit-box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .16), 0 2px 10px 0 rgba(0, 0, 0, .12) !important;
   font-weight: bold !important;
}
  
 .zx{ 
    margin-left:-0.8% !important;
    padding:0 !important;
}




</style>
</head>
<jsp:include page="../fixsection/header.jsp"></jsp:include>
<body>

   <div class="row ros">
      <div class="col-lg-12">
         <h5 class="page-header">공지사항</h5>
      </div>
      <!-- /.col-lg-12 -->
   </div>
 
   <!-- 검색 폼... -->
   <div class='row roa'>
      <div class="col-lg-12 zx">

         <form id='searchForm' action="/admin/noticeBoard" method='get'>
            <div class="sele">
            <select name='type' class="hehe">
               <option value=""
                  <c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>
               <option value="T"
                  <c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
               <option value="C"
                  <c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
               <option value="W"
                  <c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
   
            </select> 
            </div>
            
            
            
            
            <div class="keywi">
            <input type='text' name='keyword'
               value='<c:out value="${pageMaker.cri.keyword}"/>' /> 
              </div>
            
            
             <div class="move_btn">
            <button class='btn btn-default'><i class="fas fa-search"></i></button>
            </div>
            
            
            <div class="panel-heading">

               <button id='regBtn' type="button" class="btn btn-primary bbb">글쓰기</button>
            </div>
            
            
            
            
            <input type='hidden' name='pageNum'
               value='<c:out value="${pageMaker.cri.pageNum}"/>' /> 
               <input type='hidden' name='amount'
               value='<c:out value="${pageMaker.cri.amount}"/>' />
            
         </form>
      </div>
   </div>
   <!-- 본격 게시판 -->
   <div class="row">
      <div class="col-lg-12">
         <div class="panel panel-default">
            

            <!-- /.panel-heading -->
            <div class="panel-body">
               <table class="table table-striped table-bordered table-hover">
                  <thead>
                     <tr>
                        <th>#번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>수정일</th>
                     </tr>
                  </thead>

                  <c:forEach items="${list}" var="board">
                     <tr>
                        <td><c:out value="${board.no_num}" /></td>
                        <td><a
                           href='/admin/noticeGet?no_num=<c:out value="${board.no_num}"/>'><c:out
                                 value="${board.title}" /></a></td>
                        <td><c:out value="${board.admin_id}" /></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd"
                              value="${board.create_date}" /></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd"
                              value="${board.update_date}" /></td>
                     </tr>
                  </c:forEach>
               </table>
            </div>

            <!-- Modal  추가 -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
               aria-labelledby="myModalLabel" aria-hidden="true">
               <div class="modal-dialog">
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                     </div>
                     <div class="modal-body">처리가 완료되었습니다.</div>
                     <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save
                           changes</button>
                     </div>
                  </div>
                  <!-- /.modal-content -->
               </div>
               <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
            <div class='pull-center'>
               <ul class="pagination justify-content-center">


                  <c:if test="${pageMaker.prev}">
                     <li class="page-item previous"><a class="page-link"
                        href="${pageMaker.startPage -1}">Previous</a></li>
                  </c:if>

                  <c:forEach var="num" begin="${pageMaker.startPage}"
                     end="${pageMaker.endPage}">
                     <li class="page-item ${pageMaker.cri.pageNum == num ? "active":""} ">
                        <a class="page-link" href="${num}">${num}</a>
                     </li>
                  </c:forEach>

                  <c:if test="${pageMaker.next}">
                     <li class="page-item next"><a class="page-link"
                        href="${pageMaker.endPage +1 }">Next</a></li>
                  </c:if>
               </ul>
            </div>
            <!--  end Pagination -->

            <!-- 전송할 페이지 정보 -->
            <form id="actionForm" action="/admin/noticeBoard" method="get">
               <input type="hidden" name="pageNum"
                  value='${pageMaker.cri.pageNum}' /> <input type="hidden"
                  name="amount" value='${pageMaker.cri.amount}' />

            </form>


         </div>
         <!--  end panel-body -->
      </div>
      <!-- end panel -->
   </div>



</body>
<script>
   $(function() {
      var result = '<c:out value="${result}"/>';

      checkModal(result);

      history.replaceState({}, null, null);

      function checkModal(result) {

         if (result === '' || history.state) {
            return;
         }

         if (parseInt(result) > 0) {
            $(".modal-body").html(
                  "게시글 " + parseInt(result) + " 번이 등록되었습니다.");
         }

         $("#myModal").modal("show");
      }

      $("#regBtn").on("click", function() {
         if("${sessionScope.loginSession.nickname}"!='admin'){
            alert("관리자 계정이 아닙니다."+ "${sessionScope.loginSession.nickname}");
         }else{
         self.location = "/admin/noticeRegister";
         }
      });
      var actionForm = $("#actionForm");
      $(".page-item a").on("click", function(e) {

         e.preventDefault();

         console.log('click');

         actionForm.find("input[name='pageNum']").val($(this).attr("href"));
         actionForm.submit();
      });

      /*검색 script 처리*/
      var searchForm = $("#searchForm");

      $("#searchForm button").on("click", function(e) {

         if (!searchForm.find("option:selected").val()) {
            alert("검색종류를 선택하세요");
            return false;
         }

         if (!searchForm.find("input[name='keyword']").val()) {
            alert("키워드를 입력하세요");
            return false;
         }

         searchForm.find("input[name='pageNum']").val("1");
         e.preventDefault();

         searchForm.submit();

      });

   })
</script>
</html>