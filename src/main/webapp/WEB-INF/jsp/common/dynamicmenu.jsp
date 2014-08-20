<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container-left">
               <div class="accordion" id="leftMenu">
                
                <c:forEach items="${menuList}" var="menu" varStatus="count">
				<!--Header  -->
				<div class="accordion-group">

				    <!--Content if have sub menu -->
				    <c:choose>
				    <c:when test="${menu.hasChildren}">
				    <!--Access  -->
					<sec:authorize ifAnyGranted="${menu.access}">
				    <div class="accordion-heading">
                         <a class="accordion-toggle" data-toggle="collapse" data-parent="#leftMenu" href="#${menu.id}">
                               <i class="icon-home"></i> ${menu.name}
                         </a>
				    </div>
				    
				    
					<div id= "${menu.id}" class="accordion-body collapse" style="height: 0px; ">
						
	                  <div class="accordion-inner" id="parentMenu_${menu.id}">
	                      
	                      	  <c:forEach items="${menu.menuItems}" var="submenu" varStatus="subCount">
	                          
								 <sec:authorize ifAnyGranted="${submenu.access}">
		                         <div class="accordion-group">
		                         		<!--Content if have sub menu -->
				    					<c:choose>
				    					<c:when test="${submenu.hasChildren}">
										<div class="accordion-heading">
											<a class="accordion-toggle" data-toggle="collapse" data-parent="#parentMenu_${menu.id}" href="#${submenu.id}"> 
											<i class="icon-file"></i> ${submenu.name}
											</a>
										</div>
										
										
										<div id="${submenu.id}" class="accordion-body collapse" style="height: 0px;">
											<div class="accordion-inner">
												<c:forEach items="${submenu.menuItems}" var="submenu_child" varStatus="sub_childCount">
													<c:url value="${submenu_child.url}" var="subMenuChildUrl"/>
													<sec:authorize ifAnyGranted="${submenu_child.access}">
														
														<div class="accordion-heading">
	                         								<a class="accordion-toggle" href="${subMenuChildUrl}">
	                               								${submenu_child.name}
	                         								</a>
				    									</div>
														
														
													</sec:authorize>
												</c:forEach>
											</div>
										</div>
										
										</c:when>
											<c:otherwise>
												<c:url value="${submenu.url}" var="subMenuUrl" />
												<sec:authorize ifAnyGranted="${submenu.access}">
													<div class="accordion-heading">
														<a class="accordion-toggle" href="${subMenuUrl}"> <i
															class="icon-home"></i> ${submenu.name}
														</a>
													</div>
												</sec:authorize>
											</c:otherwise>

										</c:choose>
									</div>
									</sec:authorize>
								
								</c:forEach>
	                                      
	                  </div>
	                 
	                <!-- Sub-menu -->
	                </div>
	                </sec:authorize>
	                </c:when>
	                <c:otherwise>
	                	<c:url value="${menu.url}" var="menuUrl"/>
						<sec:authorize ifAnyGranted="${menu.access}">
	                	<div class="accordion-heading">
                         <a class="accordion-toggle" href="${menuUrl}">
                               <i class="icon-home"></i> ${menu.name}
                         </a>
				    	</div>
				    	</sec:authorize>
	                </c:otherwise>
	                
	                </c:choose>
	            <!--Accordion Header  -->
				</div>
				</c:forEach>
                    
                    
                <!--accordion class  -->    
                </div>
                

</div>