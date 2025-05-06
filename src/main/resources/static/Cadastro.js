document.getElementById("cadastroForm").addEventListener("submit", function(e) {
    e.preventDefault();

    let nome = document.getElementById("nome").value;
    let email = document.getElementById("emailCadastro").value;
    let senha = document.getElementById("senhaCadastro").value;

    fetch("http://localhost:8080/usuarios/criar", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ nome, email, senha })
    })
        .then(response => {
            if (response.status === 201) {
                window.location.href = "login.html"; // Redirecionar para a página de login
            } else {
                document.querySelector(".error-message").style.display = "block";
            }
        })
        .catch(error => console.error("Erro:", error));
});