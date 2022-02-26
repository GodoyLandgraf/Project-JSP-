<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  
 <title>Área de Login</title>
 
 <style type="text/css">
 form {
 position:absolute;
 text-align:center;
 top:40%;
 left:33%;
 right:33%;
 }
h4 {
 position:absolute;
  text-align:center;
 top:33%;
 left:33%;
 right:33%
 }
 .msg {
 position:absolute;
 text-align:center;
 top:10%;
 left:33%;
 right:33%;
 font-size:20px;
 color: #842029;
 background-color: #f8d7da;
 border-color: #f5c2c7;
 border-radius:5px;

 }
 </style>
 
</head>
<body>
<h4>Área de Login</h4>

<form action="<%=request.getContextPath()%>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate> 
<input type="hidden" value="<%=request.getParameter("url")%>" name="url">
<div class="mb-3">
<label class="form-label">Login: </label>
<input class="form-control" name = "login" type="text"  required> 
 <div class="valid-feedback">
      Ok!
    </div>
     <div class="invalid-feedback">
        Digite o Login!
      </div>
</div> 
<br>
<br>
<div class="mb-3">
<label class="form-label">Senha:</label>
<input class="form-control" name = "senha" type="password"  required>
 <div class="valid-feedback">
      Ok!
    </div>
     <div class="invalid-feedback">
        Digite a Senha!
      </div>
</div> 


<input class="btn btn-primary" type= "submit" value="Acessar">

</table>
</form>
<!-- a informação abaixo pega a 'msg' do requestDispatcher do servlet e passa para o navegador-->


<h5 class="msg">${msg}</h5>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script type="text/javascript">
(function () {
	  'use strict'

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  var forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }

	        form.classList.add('was-validated')
	      }, false)
	    })
	})()
</script>
</body>
</html>