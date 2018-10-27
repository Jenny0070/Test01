
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body style="margin:0 auto; width:500px;">

<table class="table table-bordered">
    <caption>新闻</caption>
    <thead>
    <tr>
        <th>题目</th>
        <th>发布人</th>
        <th>类型</th>
        <th>发布日期</th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${requestScope.page.list}" var="news">
    <tr><td>${news.title}</td><td>${news.promulgator}</td><td>${news.type}</td><td>${news.date}</td></tr>
</c:forEach>
    </tbody>
</table>
<!--当前页第一页时-->
<c:if test="${requestScope.page.pageNum==1}">
    <ul class="pagination">
        <li><a href="test?pageNum=1">首页</a></li>
    <c:forEach begin="${requestScope.page.start}" end="${requestScope.page.end}" step="1" var="i">
        <c:if test="${i==1}">
           <li class="disabled"><a href="#">1</a></li>
        </c:if>
        <c:if test="${i!=1}">
            <li><a href="test?pageNum=${i}">${i}</a></li>
        </c:if>
    </c:forEach>
        <li><a href="test?pageNum=${requestScope.page.pageNum+1}">&raquo;</a></li>
        <li><a href="test?pageNum=${requestScope.page.totalPage}">尾页</a></li>
    </ul>
</c:if>
<!--当前页是最后页时-->
<c:if test="${requestScope.page.pageNum==requestScope.page.totalPage}">
    <ul class="pagination">
        <li><a href="test?pageNum=1">首页</a></li>
        <li><a href="test?pageNum=${requestScope.page.pageNum-1}"><<</a></li>
        <c:forEach begin="${requestScope.page.start}" end="${requestScope.page.end}" step="1" var="i">
            <c:if test="${i==requestScope.page.totalPage}">
                <li class="disabled"><a href="#">${requestScope.page.totalPage}</a></li>
            </c:if>
            <c:if test="${i!=requestScope.page.totalPage}">
                <li><a href="test?pageNum=${i}">${i}</a></li>
            </c:if>
        </c:forEach>
        <li><a href="test?pageNum=${requestScope.page.totalPage}">尾页</a></li>
    </ul>
</c:if>
<!--当前页是中间页时-->
<c:if test="${requestScope.page.pageNum<requestScope.page.totalPage&&requestScope.page.pageNum>1}">
    <ul class="pagination">
        <li><a href="test?pageNum=1">首页</a></li>
        <li><a href="test?pageNum=${requestScope.page.pageNum-1}"><<</a></li>
        <c:forEach begin="${requestScope.page.start}" end="${requestScope.page.end}" step="1" var="i">
            <c:if test="${i==requestScope.page.pageNum}">
               <li class="disabled"><a href="test?pageNum=${i}">${i}</a></li>
            </c:if>
            <c:if test="${i!=requestScope.page.pageNum}">
                <li><a href="test?pageNum=${i}">${i}</a></li>
            </c:if>
        </c:forEach>
        <li><a href="test?pageNum=${requestScope.page.pageNum-1}">>></a></li>
        <li><a href="test?pageNum=${requestScope.page.totalPage}">尾页</a></li>
    </ul>
</c:if>
    <br>
</body>
</html>
