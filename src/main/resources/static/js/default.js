 
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

function deleteTodo(id) {
	alert('삭제 하시겠습니까?');
	$.ajax({
		url : '/api/tasks/' + id,
		method : 'delete',
		async : true,
		contentType : "application/json",
		success : function(resp) {
			console.log("task 삭제");
			location.href = '/';
		},
		error : function(err) {
			console.log(err.toString());
		}
	});
}

function updateGet(dataId) {
	$.ajax({
		url : '/api/tasks/' + dataId,
		method : 'get',
		async : true,
		contentType : "application/json",
		success : function(data) {
			console.log("task 수정");
			$("#update-title").val(data.title);
			$("#update-desc").val(data.content);
			// var priority = data.priority.name;
			$("#update-endDate").format(data.endDate, 'yyyy-MM-dd');
			updatemodal();
		},
		error : function(err) {
			console.log(err.toString());
		}
	});
}

function updatemodal() {
	var modal = document.querySelector(".modal[id = updateModal]");
	var trigger = document.querySelector(".trigger[id = update]");
	var closeButton = document.querySelector(".close-button");
	var cancelButton = document.querySelector("#cancel");

	function toggleModal() {
		modal.classList.toggle("show-modal");
	}

	function windowOnClick(event) {
		if (event.target === modal) {
			toggleModal();
		}
	}

	trigger.addEventListener("click", toggleModal);
	closeButton.addEventListener("click", toggleModal);
	cancelButton.addEventListener("click", toggleModal);
	window.addEventListener("click", windowOnClick);
}

$("#todo-submit").click(function() {
	var title = $("#form-title").val();
	var content = $("#form-desc").val();
	var endDate = $("#form-endDate").val();
	var priority = priorities.options[priorities.selectedIndex].value;

	var JSONObject = {
		'title' : title,
		'content' : content,
		'endDate' : endDate,
		'priorityId' : priority
	};
	var jsonData = JSON.stringify(JSONObject);

	$.ajax({
		url : '/api/tasks',
		method : 'post',
		data : jsonData,
		async : true,
		contentType : "application/json",
		success : function(data) {
			alert("추가 완료.");
		},
		error : function(err) {
			console.log(err.toString());
		}
	});
});

