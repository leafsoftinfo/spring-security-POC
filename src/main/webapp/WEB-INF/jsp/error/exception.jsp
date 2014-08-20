<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container-exception">
<h3>Boom! Exception Occured.</h3>
<div class="alert alert-${message.isError=='true'? 'error' : 'success'} alert-dismissable" style="${message==null? 'display: none;' : 'display: block;'}">
<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
<span>Oh! Snap! Encountered Error with Message: ${message.info}</span>
</div>
<spring:url value="/resources/images/t-google-404-1299071983.jpg" var="exceptionRobot"/>
<img class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" src="${exceptionRobot}"/>

<div class="alert alert-info">
<div class="accordion" id="accordion2">  
  <div class="accordion-group">  
    <div class="accordion-heading">  
      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">  
        Please click to toggle error message.  
      </a>  
    </div>  
    <div id="collapseOne" class="accordion-body collapse">  
      <div class="accordion-inner">  
        ${message.exceptionMsg}  
      </div>  
    </div>  
  </div>
</div>

</div>

</div>