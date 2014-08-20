<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/logout" var="logoutUrl"/>
<c:url value="/account/exception" var="exceptionUrl"/>
<c:url value="/account/current" var="accountUrl"/>
<c:url value="/main/common" var="mainUrl"/>

<nav class="navbar navbar-default navbar-fixed-top" style="width:1350px" role="navigation">
<div class="container">
<ul class="nav navbar-pills">
        <li><a href="${mainUrl}">Home</a></li>
        <li><a href="#">Menu</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Reports <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
        <li><a href="${exceptionUrl}">Exception Demo</a></li>
</ul> 
<ul class="nav navbar-nav navbar-right">
        <li><a href="${accountUrl}">Account Settings</a></li>
        <li><a href="#">Help</a></li>
        <li><a href="${logoutUrl}">Logout</a></li>
</ul>    
</div>
</nav>