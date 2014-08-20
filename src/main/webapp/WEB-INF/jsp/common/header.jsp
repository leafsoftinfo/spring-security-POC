<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:url value="/logout" var="logoutUrl"/>
<div class="page-header">
   <h1>Spring Security POC</h1>
</div>

<table>
<tr>
<td width="250">
<sec:authorize access="isAuthenticated()">
<span id="menu-username">Welcome : <%=SecurityContextHolder.getContext().getAuthentication().getName()%></span>
</sec:authorize>
</td>
<td width="250" align="right"><a href="${logoutUrl}">Logout</a></td>
</tr>
</table>
<c:if test="${message.isError}">
	<div id=message>  Message : ${message.info}</div>
</c:if>


<br style="clear:left"/>