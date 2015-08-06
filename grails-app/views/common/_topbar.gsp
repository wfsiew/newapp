<div id="menu">
	<nobr>
		<g:if test="${session.user}">
		    <b>${session.user?.firstName}&nbsp;${session.user?.lastName}</b> |
            <g:link controller="users" action="logout">Logout</g:link>
	    </g:if>
	    <g:else>
	        <g:link controller="users" action="login">Login</g:link>
	    </g:else>
	</nobr>
</div