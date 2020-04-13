<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
	  <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="${ empty sessionScope.name ? '/Mini-Managing-Employees/' : '/Mini-Managing-Employees/dashboard' }">Managing-Employees</a>
	  <ul class="navbar-nav px-3">
	    <li class="nav-item text-nowrap">
	    <c:if test="${ !empty sessionScope.name }"> 
	    	<a class="nav-link" href="/Mini-Managing-Employees/signout?out=1">Sign out</a>
	    </c:if>
	    <c:if test="${ empty sessionScope.name }"> 
	    	<a class="nav-link" href="/Mini-Managing-Employees/login">Login</a>
	    </c:if>
	      
	    </li>
	  </ul>
	</nav>