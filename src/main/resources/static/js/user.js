let index = {

/*
	let  _this = this;
	init: function() {
		$("#btn-save").on("click", function() { //  function () => {} this를 바인딩 하기 위해서!!
			_this.save();
		});
	},
*/

	init: function() {
		$("#btn-save").on("click", () => { //  function () => {} this를 바인딩 하기 위해서!!
			this.save();
		});
		$("#btn-update").on("click", () => { //  function () => {} this를 바인딩 하기 위해서!!
			this.update();
		});
		
//		$("#btn-login").on("click", () => { //  function () => {} this를 바인딩 하기 위해서!!
//			this.login();
//		});
	},

	save: function() {
		//alert('user save 함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		console.log(data);  //자바스크립트 오브젝트
		console.log(JSON.stringify(data)); //JSON string
		// ajax호출시 default가 비동기 호출 
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
		//ajax가 통신을 성공하고 서버가  json을 리턴해주면 자동으로 자바 오브젝트로 반환해주네요.
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //body data 데이터
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json) => javascript
		}).done(function(resp){
			if (resp.status === 500){
			alert("회원가입에 실패하였습니다.");
			}else{
			alert("회원가입이 완료되었습니다.");
			location.href="/";
			}
			console.log(resp);
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}, 
	update: function() {
		// let id =  $("#id").val();
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		console.log(data);  //자바스크립트 오브젝트
		console.log(JSON.stringify(data)); //JSON string
		// ajax호출시 default가 비동기 호출 
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
		//ajax가 통신을 성공하고 서버가  json을 리턴해주면 자동으로 자바 오브젝트로 반환해주네요.
		$.ajax({
			//회원가입 수행 요청
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), //body data 데이터
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json) => javascript
		}).done(function(resp){
			alert("회원수정이 완료되었습니다.");
			console.log(resp);
			// location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}, 	
/*
	login: function() {
		//alert('user save 함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};
		console.log(data);  //자바스크립트 오브젝트
		console.log(JSON.stringify(data)); //JSON string
		// ajax호출시 default가 비동기 호출 
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
		//ajax가 통신을 성공하고 서버가  json을 리턴해주면 자동으로 자바 오브젝트로 반환해주네요.
		$.ajax({
			//로그인 수행 요청
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data), //body data 데이터
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json) => javascript
		}).done(function(resp){
			alert("로그인이 완료되었습니다.");
			console.log(resp);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	} 
	*/	
}

index.init();
