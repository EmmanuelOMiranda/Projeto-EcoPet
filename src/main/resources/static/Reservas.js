document.getElementById("reservasForm").addEventListener("submit", function(e) {
    e.preventDefault();

    // Captura valores
    const petName = document.getElementById("pet-name").value;
    const date = document.getElementById("date").value;
    const time = document.getElementById("time").value;
    const notes = document.getElementById("notes").value;

    // Constrói payload
    const payload = {
        dataEntrada: date,
        // supondo que tenha dataSaida = date + 1 exemplo
        dataSaida: date,
        // será necessário obter animalId do pet cadastrado
        animalId: "ID_DO_PET",
        usuarioId: "ID_DO_TUTOR"
    };

    fetch(`http://localhost:8080/reservas/criar?animalId=${payload.animalId}&userId=${payload.usuarioId}`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(payload)
    })
        .then(res => {
            if (res.ok) {
                document.querySelector(".success-message").style.display = "block";
                document.querySelector(".error-message").style.display = "none";
            } else {
                throw new Error("Falha na reserva");
            }
        })
        .catch(() => {
            document.querySelector(".error-message").style.display = "block";
            document.querySelector(".success-message").style.display = "none";
        });
});