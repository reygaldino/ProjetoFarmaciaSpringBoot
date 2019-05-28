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

$(document).ready(function () { 
    var $seuCampoPrice = $("#PRICE");
    $seuCampoPrice.mask('000,00', 
    {reverse: true});
    
});

$(document).ready(function () { 
    var $seuCampoRg = $("#rg");
    $seuCampoRg.mask('0.000-000', 
    {reverse: true});
    
});

$(document).ready(function () { 
    var $seuCampoDataF = $("#dataFab");
    $seuCampoDataF.mask('00/00/0000', {reverse: true});
});

$(document).ready(function () { 
    var $seuCampoDataV = $("#dataVenc");
    $seuCampoDataV.mask('00/00/0000', {reverse: true});
});

$(document).ready(function () { 
    var $seuCampoQuant = $("#dataVenc");
    $seuCampoQuant.mask('00/00/0000', {reverse: true});
});

$(document).ready(function () { 
    var $seuCampoTel = $("#telephone");
    $seuCampoTel.mask("(99) 99999-9999");
});
