//회원정보수정 버튼

function update(userId){

	let data = $("#profileUpdate").serialize();
	

	
	$.ajax({
		
		type:"put",
		url:`/api/user/${userId}`,
		data: data, 
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		data:"json"
	}).done(res=>{
		console.log("성공");
	}).fail(error=>{
		console.log("실패");
		 
	});
}