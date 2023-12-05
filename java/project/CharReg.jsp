<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script> function fnError(){
	alert("내용을 모두 입력하세요");
	history.back(); }</script>
	

<title>충전기 등록</title>
<jsp:useBean id="dto" class="chr.CharDTO"/>
<jsp:setProperty name="dto" property="*"/>
<jsp:useBean id="dao" class="chr.CharDAO"/>
</head>
<body>
<section>
<fieldset class="fset">

<% if(dto.getCharger_name()==null || dto.getCharger_port()==null || dto.getCharger_output() == null ||
dto.getCharger_quick()==null || dto.getCharger_name().trim()==null || dto.getCharger_port().trim()==null ||
dto.getCharger_output().trim()==null || dto.getCharger_quick().trim()==null ){
%>

	<section><script>fnError();</script></section>
	<% return; 
	}
%>
<% 
int rec_no = dao.insertCharger(dto.getCharger_name(), dto.getCharger_port(), dto.getCharger_output(), dto.getCharger_quick(), dto.getCharger_date());
if(rec_no > 0 ){
	%>
<script> alert("성공");
location.href="./CharHome.jsp";</script>
<% } else { %>
<script> alert("실패");
location.href="./CharHome.jsp";</script>
<% } %>

}

</fieldset>
</section>

</body>
</html>


