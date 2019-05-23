$(document).ready(function () { 
        var $seuCampoCpf = $("#CPF");
        $seuCampoCpf.mask('000.000.000-00',
        {reverse: true});
        
});

$(document).ready(function () { 
    var $seuCampoSalary = $("#salary");
    $seuCampoSalary.mask('0.000,00', 
    {reverse: true});
    
});
