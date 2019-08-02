 
// 마감 기한 선택 시 지난 날짜는 선택할 수 없도록 함.
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1;
var yyyy = today.getFullYear();

if (dd < 10) {
	dd = '0' + dd
}
if (mm < 10) {
	mm = '0' + mm
}
today = yyyy + '-' + mm + '-' + dd;
document.getElementById("form-endDate").setAttribute("min", today);
document.getElementById("update-form-endDate").setAttribute("min", today);


function loadPage(page){
    var url = '/api/tasks'
        if(page != null){
            url = url + '?page=' + page
        }
    $.ajax({
		url : url,
		method : 'get',
		async : true,
		contentType : "application/json",
		success : function(resp) {
			console.log("page 불러오기");
			totalPage: resp.totalPages;
            $('#now-page').text(nowPage);
            if(nowPage!=totalPage){
            	$('#total-page').text(totalPage);
            } else{
            	$('#total-page').hide();
            }
            var todoDiv = "#todo-div"
                var title = ".title"
                var desc = ".desc"
                var endDate = ".end-date"
                var regDate = ".reg-date"
                var priority = "#priority"
                var complete = "#customCheck"
                var todoid = "#todo-id"
                for(var i = 0; i < resp.numberOfElements; i++){
                    if( resp.content[i].complete == true){
                        $( complete+i ).prop("checked" , true);
                        $( title+i ).addClass("todo-done-text");
                        $( desc+i ).addClass("todo-done-text");
                    }else{
                        $( complete+i ).prop("checked" , false);
                        $( title+i ).removeClass("todo-done-text");
                        $( desc+i ).removeClass("todo-done-text");
                    }
                }
/*
 * $( todoDiv+i ).removeClass('todo-display-none');
 */                    $( todoid+i ).val(resp.content[i].id);
                    $( title+i ).text(resp.content[i].title);
                    $( desc+i).text(resp.content[i].content);
                    var endDate =resp.content[i].endDate.slice(0,10);
                    var regDate =resp.content[i].regDate.slice(0,10);
                    $( regDate+i).text(regDate);
                    $( endDate+i ).text(endDate);
                    $( priority+i ).text(resp.content[i].priority);
                    removePriorityClass(priority+i);
                    $( priority+i ).addClass(setPriorityClass(resp.content[i].priority));
            
		},
		error : function(err) {
			console.log(err.toString());
		}
	});
}

function deleteTodo(id) {
	$.ajax({
		url : '/api/tasks/' + id,
		method : 'delete',
		async : true,
		contentType : "application/json",
		success : function(resp) {
			console.log("task 삭제");
			alert("삭제 되었습니다.");
			location.href = '/';
		},
		error : function(err) {
			console.log(err.toString());
		}
	});
}

function updateGet(id) {
    $("#update-todo-id").val(id);
    if(id == null) return;
    $.ajax({
        url: '/api/tasks/' + id,
        method: 'get',
        async: true,
        contentType: "application/json",
        success: function (resp) {
            $("#update-form-title").val(resp.title);
            $("#update-form-desc").val(resp.content);
            var priority = resp.priority;
            $("select option[value="+priority+"]").attr("selected", true);
            $("#update-form-endDate").val(resp.endDate.slice(0,10));
        },
        error: function (err) {
            console.log(err.toString());
        }
    })
}

$("#todo-submit").click(function() {
	var title = $("#form-title").val();
	var desc = $("#form-desc").val();
	var endDate = $("#form-endDate").val();
	var priority = priorities.options[priorities.selectedIndex].value;

	var JSONObject = {
		'title' : title,
		'content' : desc,
		'endDate' : endDate,
		'priority' : priority
	};
	var jsonData = JSON.stringify(JSONObject);

	$.ajax({
		url : '/api/tasks',
		method : 'post',
		data : jsonData,
		async : true,
		contentType : "application/json",
		success : function(resp) {
			alert("추가 완료.");
			location.href = '/';
		},
		error : function(err) {
			console.log(err.toString());
		}
	});
});

$("#todo-update").click( function() {
    console.log('업데이트 시작');
    var id = $("#update-todo-id").val();
    var title = $("#update-form-title").val();
    var desc = $("#update-form-desc").val();
    var endDate = $("#update-form-endDate").val();
    var priority = priorities.options[priorities.selectedIndex].value;
    console.log('update modal: '+id+'/' + title + '/'+ desc + '/' + endDate +'/' + priority);
    var JSONObject = {
        'id': id,
        'title': title,
        'content' : desc,
        'priority' : priority,
        'endDate' : endDate
     };
    var jsonData = JSON.stringify(JSONObject);
    $.ajax({
        url: '/api/tasks/' + id,
        method: 'put',
        data: jsonData,
        async: true,
        contentType: "application/json",
        success: function (resp) {
            console.log("업데이트 완료");
            alert("수정 완료");
            location.reload();
        },
        error: function (err) {
            console.log(err.toString());
        }
    });
    $("#form-title-update").val('');
    $("#form-desc-update").val('');
    $('input[name="priorityRadioUpdate"]').prop("checked" , false);
    $("#deadline-date-update").val('');
    $("#update-todo-index").val('');
    $('#updateModal').modal('toggle');
});

function prev(){
    if(nowpage <= totalpage && nowpage > 1){
        nowpage = nowpage - 1;
        loadPage(nowpage);
    }else{
    	document.getElementById("previous").setAttribute("disabled", "true");
    }
}

function next(){
    if(nowpage < totalpage){
        nowpage = nowpage + 1;
        loadPage(nowpage);
    }else{
    	document.getElementById("next").setAttribute("disabled", "true");
    }
}
