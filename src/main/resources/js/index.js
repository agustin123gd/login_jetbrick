var correo;
var id;

var btnEnviar = document.getElementById("btnEnviar");
btnEnviar.addEventListener("click", () => {

    var params = new URLSearchParams();
    var email = document.getElementById("email").value;
    var pass = document.getElementById("password").value;
    params.append("email", email);
    params.append("password", pass);
    axios.get("http://localhost:4567/entrar?"+params)
        .then(function (rs) {
            console.log(rs.data);
        })
        .catch(function (error) {
            console.log(error);
        });
});