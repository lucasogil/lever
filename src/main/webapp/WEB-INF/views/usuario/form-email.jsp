<%--
  Created by IntelliJ IDEA.
  User: Lucas Gil
  Date: 05/09/2018
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="lever" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<lever:template>
    <jsp:body>
        <div class=" col-md-6 col-md-offset-3">
            <form action="/usuario/request" method="post">
                <span class="text-danger">${param.error}</span>

                <div class="form-group">
                    <label for="login">E-mail:</label>
                    <input id="login" type="text" name="email" class="form-control">
                </div>

                <button class="btn btn-primary" type="submit">Solicitar	Acesso</button>
            </form>
        </div>
    </jsp:body>
</lever:template>
