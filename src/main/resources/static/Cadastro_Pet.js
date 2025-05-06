document.getElementById("cadastroPetForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const userId = document.getElementById("userId").value;
    const nome = document.getElementById("nomePet").value;
    const tipo = document.getElementById("tipoPet").value;
    const raca = document.getElementById("racaPet").value;
    const idade = document.getElementById("idadePet").value;
    const porte = document.getElementById("portePet").value;
    const necessidadesEspeciais = document.getElementById("necessidadesPet").value;

    const petData = {
        nome,
        tipo,
        raca,
        idade,
        porte,
        necessidadeEspeciais: necessidadesEspeciais
    };

    fetch(`http://localhost:8080/animais/cadastrar?userId=${userId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(petData)
    })
        .then(response => {
            if (response.ok) {
                alert("Pet cadastrado com sucesso!");
                window.location.href = "reservas.html"; // segue para reservas
            } else {
                throw new Error("Falha no cadastro");
            }
        })
        .catch(err => {
            console.error(err);
            document.querySelector(".error-message").style.display = "block";
        });
});