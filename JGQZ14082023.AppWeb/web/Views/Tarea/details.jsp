<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jgqz14082023.entidadesdenegocio.Tarea"%>
<% Tarea tarea = (Tarea) request.getAttribute("tarea");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle de tarea</title>
    </head>
    
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle de tarea</h5>
             <div class="row">
                    <div class="input-field col l8 s12">
                        <input  id="txtTitulo" type="text" value="<%=tarea.getTitulo()%>" disabled>
                        <label for="txtTitulo">Titulo</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input id="txtDescripcion" type="text" value="<%=tarea.getDescripcion() %>" disabled>
                        <label for="txtDescripcion">Descripcion</label>
                    </div> 
                </div>

                <div class="row">
                    <div class="col l12 s12">
                         <a href="Tarea?accion=edit&id=<%=tarea.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>            
                        <a href="Tarea" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />    
</html>