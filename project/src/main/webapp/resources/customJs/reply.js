/**
 * 댓글 작성 스크립트
 */

console.log("Reply Module........");

var replyService = (function() {

	function add(reply, callback, error) {
		console.log("add reply...............");

		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}

	function getList(param, callback, error) {
		/* param 이 배열로 들어온다 */
		var wa_num = param.wa_num;
		var page = param.page || 1;

		$.getJSON("/replies/pages/" + wa_num + "/" + page + ".json",
				function(data) {
					if (callback) {

						// callback(data); 이작시은 댓글 들만 가지고옴
						callback(data.replyCnt, data.list); // 댓글 숫자와 , 댓글들을 가지고옴
					}
				}).fail(function(xhr, status, err) {
			if (error) {
				error(er);
			}
		});
	}

	function remove(reply_num, callback, error) {
		$.ajax({
			type : 'delete',
			url : '/replies/' + reply_num,
			success : function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}

		});
	}

	function update(reply, callback, error) {
		console.log("reply_num : " + reply.reply_num);

		$.ajax({
			type : 'put',
			url : '/replies/' + reply.reply_num,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}

	function get(reply_num, callback, error) {
		$.get("/replies/" + reply_num + ".json", function(result) {
			if (callback) {
				callback(result);
			}
		}).fail(function(xhr, status, err) {
			if (error) {
				error();
			}
		});
	}

	function displayTime(timeValue) {

		var today = new Date();

		var gap = today.getTime() - timeValue;

		var dateObj = new Date(timeValue);
		var str = "";

		if (gap < (1000 * 60 * 60 * 24)) {

			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();

			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
					':', (ss > 9 ? '' : '0') + ss ].join('');

		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
			var dd = dateObj.getDate();

			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
					(dd > 9 ? '' : '0') + dd ].join('');
		}
	}

	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get,
		displayTime : displayTime

	};
})();
