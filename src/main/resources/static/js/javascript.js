$(document).ready(function () { 
        var $seuCampoCpf = $("#CPF");
        $seuCampoCpf.mask('000.000.000-00',
        {reverse: true});
        
});

$(document).ready(function () { 
    var $seuCampoSalary = $("#salary");
    $seuCampoSalary.mask('0.000.00', 
    {reverse: true});
    
});

token = request.POST["h-captcha-response"]
params = { 
   "secret": "0xf13d42C4B74E2c07F22E80D90c1630C3fcb4f453",
   "response": token
}
json = http.POST("https://hcaptcha.com/siteverify", params)
