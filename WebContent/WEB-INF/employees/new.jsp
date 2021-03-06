<%@ include file="../layouts/header.jsp" %>
<meta name="theme-color" content="#563d7c">
<%@ include file="../assets/css/dashboard.css" %>
<title>New</title>
  </head>
  <body>
    <%@ include file="../layouts/menubar.jsp" %>

<div class="container-fluid">
  <div class="row">
    <%@ include file="../layouts/sidebar.jsp" %>

    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
      </div>

      <h2>New Employee</h2>
      <div class="table-responsive">
        <form action="new" method="post">
			<div class="form-group">
			    <label for="name">Name</label>
			    <input type="text" class="form-control ${ empty not_name ? '' : 'is-invalid' }" id="name" name="name" value="${oldname }" />
			    <c:if test="${!empty not_name}">
			    	<small class="invalid-feedback">
			    		<c:out value="${not_name}"/>
			    	</small>
			    </c:if>
			  </div>
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
			    <label for="tel">Phone</label>
			    <input type="text" class="form-control ${ empty not_tel ? '' : 'is-invalid' }" id="tel" name="tel" value="${oldtel}"/>
			    <c:if test="${!empty not_tel}">
			    	<small class="invalid-feedback">
			    		<c:out value="${not_tel}"/>
			    	</small>
			    </c:if>
			  </div>
			  <div class="form-group">
			    <label for="HireDate">Hire Date</label>
			    <input type="date" class="form-control ${ empty not_HireDate ? '' : 'is-invalid' }" id="HireDate" name="HireDate" value="${ empty oldHireDate ? today : oldHireDate }"/>
			    <c:if test="${!empty not_HireDate}">
			    	<small class="invalid-feedback">
			    		<c:out value="${not_HireDate}"/>
			    	</small>
			    </c:if>
			  </div>
			  <div class="form-group">
			    <label for="salary">Salary</label>
			    <input  type="number" step="0.01" class="form-control ${ empty not_salary ? '' : 'is-invalid' }" id="salary" name="salary" value="${ empty oldsalary ? '0.00' : oldsalary }"/>
			    <c:if test="${!empty not_salary}">
			    	<small class="invalid-feedback">
			    		<c:out value="${not_salary}"/>
			    	</small>
			    </c:if>
			  </div>
			  <div class="form-group">
			   <button type="save" class="btn btn-secondary">Submit</button>
			  </div>
			 
			</form>
      </div>
    </main>
  </div>
</div>

<%@ include file="../layouts/footer.jsp" %>