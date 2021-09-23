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
		location.href="/user/profile"
	}).fail(error=>{
		if(error.responseJSON.data.name === '공백일 수 없습니다'){
			
			alert("이름은 공백일 수 없습니다.");
			location.reload();
		
		}else if(error.responseJSON.data.password === '공백일 수 없습니다'){
			
			alert("패스워드는 공백일 수 없습니다.");
				location.reload();
		}
		
		//alert(JSON.stringify(error.responseJSON.data)); //js오브젝트를 json문자열로 변환.
		else{
			alert("이름과 패스워드는 공백일 수 없습니다.");
			
		}
		 
	});
}