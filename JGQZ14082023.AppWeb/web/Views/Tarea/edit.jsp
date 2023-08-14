<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jgqz14082023.entidadesdenegocio.Tarea"%>
<% Tarea tarea = (Tarea) request.getAttribute("tarea");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar tarea</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar tarea</h5>
            <form action="Tarea" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=tarea.getId()%>">  
                <div class="row">
                    <div class="input-field col l8 s12">
                        <input  id="txtTitulo" type="text" name="titulo" value="<%=tarea.getTitulo()%>" required class="validate" maxlength="50">
                        <label for="txtTitulo">Titulo</label>
                    </div>
                    <div class="input-field col l3 s12">
                        <input id="txtDescripcion" type="text" name="descripcion" value="<%=tarea.getDescripcion()%>">
                        <label for="txtDescripcion">Descripcion</label>
                    </div>
                </div>
                        <div class="row center">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Tarea" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />   
        <script>
            function validarFormulario() {
                var result = true;
                var slTarea = document.getElementById("slTarea");
                var slTarea_error = document.getElementById("slTarea_error");
                if (slTarea.value == 0) {
                    slTarea_error.innerHTML = "La Tarea es obligatorio";
                    result = false;
                } else {
                    slTarea_error.innerHTML = "";
                }
                return result;
            }
        </script>
    </body>
</html>