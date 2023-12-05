<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="chr.CharDTO" %>
<%@ page import="chr.CharDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <title>충전기 대여</title>
    <script>
        function toggleRental(button) {
            if (button.innerHTML === '대여') {
                button.innerHTML = '반납';
            } else {
                button.innerHTML = '대여';
            }
        }
    </script>
</head>

<body>
    <% 
        request.setCharacterEncoding("UTF-8");
    
        String charDivide = "전체";
        String search = "";
        int pageNumber = 0;

        if (request.getParameter("charDivide") != null) {
            charDivide = request.getParameter("prodDivide");
        }
        if (request.getParameter("search") != null) {
            search = request.getParameter("search");
        }

        List<CharDTO> chargerList = new CharDAO().findAllProducts();
        List<CharDTO> charlist = new ArrayList<CharDTO>();

        if (charDivide.equals("전체")) {
            for (CharDTO charger : chargerList) {
                charlist.add(charger);
            }
        }
    %>

    <header>
        <div class="headr">
            <h1 style="color:blue;">충전기 대여</h1>
        </div>
    </header>
    
    <nav class ="navbar">
        <ul class="navbar__menu">
            <li><a href="CharReg.html">충전기 등록</a></li>
        </ul>
       <br />
    </nav>

    <div class="container">
        <form method="post" action="./CharHome.jsp" class="form-inline mt-3">

        </form>

        <table class="table">
            <thead>
                <tr>
                    <th onclick="sortTable(0)">충전기 이름</th>
                    <th onclick="sortTable(1)">충전기 포트개수</th>
                    <th onclick="sortTable(2)">충전기 출력</th>
                    <th onclick="sortTable(3)">급속 충전여부</th>
                    <th>동작</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (charlist != null) {
                        for (CharDTO charger : charlist) {
                %>
                            <tr>
                                <td><%= charger.getCharger_name() %></td>
                                <td><%= charger.getCharger_port() %></td>
                                <td><%= charger.getCharger_output() %></td>
                                <td><%= charger.getCharger_quick() %></td>
                                <td><%= charger.getCharger_date() %></td>
                                <td>
                                    <button onclick="toggleRental(this)">대여</button>
                                </td>    
                            </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>

    </div>
</body>

</html>
