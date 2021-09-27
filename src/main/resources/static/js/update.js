//회원정보수정 버튼

function update(userId, event){
	event.preventDefault();//폼태그 서브밋액션 막기
	
	let data = $("#profileUpdate").serialize();
	

	
	$.ajax({
		
		type:"put",
		url:`/api/user/${userId}`,
		data: data, 
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json"
	}).done(res=>{//http200번대
		console.log("성공",res);
		alert("회원정보수정이 완료되었습니다");
		location.href="/user/profile"
	}).fail(error=>{
		console.log(error.responseJSON);
		
		if(error.responseJSON == null){
			alert(error.responseJSON.message);
		}
		else{
			alert(error.responseJSON.message);
		}
		//alert(JSON.stringify(error.responseJSON.data)); //js오브젝트를 json문자열로 변환.

		});
}
		 

