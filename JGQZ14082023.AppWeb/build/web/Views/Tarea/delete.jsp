<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jgqz14082023.entidadesdenegocio.Tarea"%>
<% Tarea tarea = (Tarea) request.getAttribute("tarea");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar tarea</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar tarea</h5>
            <form action="Tarea" method="post">  
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=tarea.getId()%>">  
                <div class="row">
                    <div class="input-field col l8 s12">
                        <input  id="txtTitulo" type="text" value="<%=tarea.getTitulo()%>" disabled>
                        <label for="txtTitulo">Titulo</label>
                    </div>
                    <div class="input-field col l3 s12">
                        <input  id="txtDescripcion" type="text" value="<%=tarea.getDescripcion()%>" disabled>
                        <label for="txtDescripcion">Descripcion</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Tarea" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>