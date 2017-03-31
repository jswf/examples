var isActive = true;

$().ready(function () {
    //EITHER USE A GLOBAL VAR OR PLACE VAR IN HIDDEN FIELD
    //IF FOR WHATEVER REASON YOU WANT TO STOP POLLING
    pollServer();
});

function pollServer()
{
    if (isActive)
    {
        window.setTimeout(function () {
            $.ajax({
                url: "/test",
                type: "POST",
                success: function (result) {
                    //SUCCESS LOGIC
                    pollServer();
                },
                error: function () {
                    //ERROR HANDLING
                    pollServer();
                }});
        }, 2500);
    }
}