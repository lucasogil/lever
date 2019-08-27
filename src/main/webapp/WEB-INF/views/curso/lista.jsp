<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="lever"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<lever:template>
	<jsp:body>
		<div class=" col-md-6 col-md-offset-3">
		<table class="table table-hover ">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Duração</th>
					<!-- <th>Preço</th> -->
					<th colspan="2" class="text-center">Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="curso" items="${cursos}">
					<tr>
						<td>${curso.nome}</td>
						<td>${curso.duracao.toMinutes()}</td>
				<%--		<td>${curso.preco}</td>			--%>
						<td>
							<a onclick="excluir(${curso.id})" class="btn btn-danger">Excluir</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-md-6 col-md-offset-3">
			<a href="/admin/curso" class="btn btn-block btn-info">Novo</a>
		</div>
		</div>
		<script>
			function excluir(id) {
				var url = window.location.href;
				$.ajax({
					url:"/admin/curso/" + id,
					type: 'DELETE',
					success: function (result) {
						console.log(result);

						window.location.href = url;
					}
				});
			}
		</script>
	</jsp:body>
</lever:template>
