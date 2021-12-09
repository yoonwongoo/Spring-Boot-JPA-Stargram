//회원정보수정 버튼

function update(userId, event){
	event.preventDefault();//폼태그 서브밋액션 막기
	
	let data = $("#profileUpdate").serialize();// 데이터에 값들이 다담김.
	

	
	$.ajax({
		
		type:"put",
		url:`/api/user/${userId}`,
		data: data, 
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json" //응답받을때 ,,json형태를 jsobject로 바꿔서 받으
	}).done(res=>{//http200번대
		console.log("성공",res);
		alert("회원정보수정이 완료되었습니다");
		location.href=`/user/${userId}`;
	}).fail(error=>{
		console.log(error.responseJSON);
		
		if(error.responseJSON.data != null){
			alert(error.responseJSON.message);
	    }else{
		alert(error.responseJSON.message);
	}
		console.log("실행",error);
		});
}
		 

