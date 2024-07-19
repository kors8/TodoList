document.addEventListener("DOMContentLoaded", function() {
    var modal = document.getElementById("modal");
    var span = document.getElementsByClassName("close")[0];

    if (modal && span) {
        span.onclick = function() {
            closeModal();
        }

        window.onclick = function(event) {
            if (event.target == modal) {
                closeModal();
            }
        }
    } else {
        console.error("Modal or close element not found.");
    }
});

function openModal(id, description) {
    document.getElementById("taskName").value = description;
    var modal = document.getElementById("modal");
    modal.classList.add("show");
    modal.style.display = "block";

    document.getElementById("saveChanges").onclick = function() {
        // Логіка збереження змін
        closeModal();
    };

    document.getElementById("deleteTask").onclick = function() {
        // Логіка видалення завдання
        closeModal();
    };
}

function closeModal() {
    var modal = document.getElementById("modal");
    modal.classList.remove("show");
    setTimeout(function() {
        modal.style.display = "none";
    }, 300);
}
