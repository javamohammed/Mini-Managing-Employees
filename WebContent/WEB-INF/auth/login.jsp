<%@ include file="../layouts/header.jsp" %>
<meta name="theme-color" content="#563d7c">
<%@ include file="../assets/css/dashboard.css" %>
<title>Login</title>
  </head>
  <body>
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
  <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="/Mini-Managing-Employees/">Managing-Employees</a>
</nav>

<div class="container-fluid">
  <div class="row">
    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
      </div>
      <div class="table-responsive">
		<form method="post" action="login">
			  <div class="form-group">
			    <label for="email">Email address</label>
			    
			    <input type="email" class="form-control ${ empty not_email ? '' : 'is-invalid' }" id="email" name="email" aria-describedby="emailHelp" value="${oldemail }"/>
			    <c:if test="${!empty not_email}">
			    	<small class="invalid-feedback">
			    		<c:out value="${not_email}"/>
			    	</small>
			    </c:if>
			  </div>
			  <div class="form-group">
			    <label for="password">Password</label>
			    <input type="password" class="form-control ${ empty not_password ? '' : 'is-invalid' }" id="password" name="password"/>
			    <c:if test="${!empty not_password}">
			    	<small class="invalid-feedback">
			    		<c:out value="${not_password}"/>
			    	</small>
			    </c:if>
			  </div>
			  <div class="form-group">
			   <button type="submit" class="btn btn-secondary">Submit</button>
			  </div>
			 
			  
			  <div class="form-group">
			    <label for="op">Don't have an account yet ? click <a href="/Mini-Managing-Employees/register" class="btn btn-link">Here</a></label>
			  </div>
			</form>
      </div>
    </main>
  </div>
</div>

<%@ include file="../layouts/footer.jsp" %>