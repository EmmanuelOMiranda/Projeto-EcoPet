document.getElementById("loginForm").addEventListener("submit", function(e) {
    e.preventDefault();

    let email = document.getElementById("email").value;
    let senha = document.getElementById("senha").value;

    fetch("http://localhost:8080/usuarios/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, senha })
    })
        .then(response => response.json())
        .then(data => {
            if (data === "Autenticação bem-sucedida") {
                window.location.href = "home.html"; // Redirecionar para a página principal
            } else {
                document.querySelector(".error-message").style.display = "block";
            }
        })
        .catch(error => console.error("Erro:", error));
});