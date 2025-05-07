//mettere le funzioni tipo controlla sessione e quelle che si ripetono sempre per ottimizazre il codice
function controllaAutenticazione(ruolo) 
{
    const utenteJSON = sessionStorage.getItem("utente");

    if (!utenteJSON) 
    {
        alert("Accesso non autorizzato.");
        window.location.href = "../index.html";
        return;
    }

    const utente = JSON.parse(utenteJSON);
    if (utente.ruolo !== ruolo) 
    {
        alert("Accesso riservato");
        window.location.href = "../index.html";
        return;
    }
}

function logout() 
{
    sessionStorage.removeItem("utente");
    window.location.href = "../index.html";
}