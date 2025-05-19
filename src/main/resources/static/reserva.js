document.getElementById("formReserva").addEventListener("submit", async function(event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    const dadosReserva = {
        cpf: document.getElementById("cpf").value,
        email: document.getElementById("email").value,
        telefone: document.getElementById("telefone").value,
        nomePet: document.getElementById("nome_pet").value,
        raca: document.getElementById("raca").value,
        tipo: document.getElementById("tipo").value,
        dataReserva: document.getElementById("data_reserva").value,
        observacoes: document.getElementById("observacoes").value
    };

    try {
        const response = await fetch("http://localhost:8080/reservas", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dadosReserva)
        });

        if (!response.ok) {
            throw new Error("Erro ao enviar reserva.");
        }

        const reservaCriada = await response.json();
        const id = reservaCriada.id;

        // Redireciona para a página de confirmação com o ID na URL
        window.location.href = `reserva-confirmada.html?id=${id}`;

    } catch (erro) {
        console.error("Erro ao enviar reserva:", erro);
        alert("Ocorreu um erro ao enviar sua reserva. Tente novamente.");
    }
});
document.getElementById("reserva-form").addEventListener("submit", function(e) {
    e.preventDefault();
    const nome = document.getElementById("nome").value;
    const data = document.getElementById("data").value;

    if (nome && data) {
        document.getElementById("mensagem").innerText = "Reserva feita com sucesso!";
        this.reset();
    } else {
        document.getElementById("mensagem").innerText = "Por favor, preencha os campos obrigatórios.";
    }
    document.getElementById("formReserva").addEventListener("submit", async function (e) {
        e.preventDefault();

        const reserva = {
            cpf: document.getElementById("cpf").value,
            email: document.getElementById("email").value,
            telefone: document.getElementById("telefone").value,
            nomePet: document.getElementById("nomePet").value,
            raca: document.getElementById("raca").value,
            tipo: document.getElementById("tipo").value,
            dataReserva: document.getElementById("dataReserva").value,
            observacoes: document.getElementById("observacoes").value
        };

        try {
            const resposta = await fetch("http://localhost:8080/api/reservas", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(reserva)
            });

            if (!resposta.ok) {
                throw new Error("Erro ao enviar a reserva");
            }

            const reservaCriada = await resposta.json();
            window.location.href = `reserva-confirmada.html?id=${reservaCriada.id}`;
        } catch (erro) {
            console.error("Erro ao enviar reserva:", erro);
            alert("Erro ao confirmar reserva.");
        }
    });
});