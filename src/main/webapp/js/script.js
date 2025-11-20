function validarFormulario() {
    const titulo = document.getElementById('titulo');
    const autorDiretor = document.getElementById('autorDiretor');
    const anoLancamento = document.getElementById('anoLancamento');
    const genero = document.getElementById('genero');
    const tipoMidia = document.getElementById('tipoMidia');
    if (!titulo || titulo.value.trim() === '') {
        alert('Por favor, preencha o tÃ­tulo!');
        if (titulo) titulo.focus();
        return false;
    }
    if (titulo.value.trim().length < 2) {
        alert('O tÃ­tulo deve ter pelo menos 2 caracteres!');
        titulo.focus();
        return false;
    }
    if (!autorDiretor || autorDiretor.value.trim() === '') {
        alert('Por favor, preencha o autor/diretor!');
        if (autorDiretor) autorDiretor.focus();
        return false;
    }
    if (autorDiretor.value.trim().length < 2) {
        alert('O nome do autor/diretor deve ter pelo menos 2 caracteres!');
        autorDiretor.focus();
        return false;
    }
    if (!anoLancamento || anoLancamento.value === '') {
        alert('Por favor, preencha o ano de lanÃ§amento!');
        if (anoLancamento) anoLancamento.focus();
        return false;
    }
    const ano = parseInt(anoLancamento.value);
    const anoAtual = new Date().getFullYear();
    if (isNaN(ano) || ano < 1800 || ano > anoAtual + 5) {
        alert(`O ano deve estar entre 1800 e ${anoAtual + 5}!`);
        anoLancamento.focus();
        return false;
    }
    if (!genero || genero.value.trim() === '') {
        alert('Por favor, preencha o gÃªnero!');
        if (genero) genero.focus();
        return false;
    }
    if (!tipoMidia || tipoMidia.value === '') {
        alert('Por favor, selecione o tipo de mÃ­dia!');
        if (tipoMidia) tipoMidia.focus();
        return false;
    }
    return true;
}
function confirmarExclusao() {
    return confirm('Tem certeza que deseja excluir este item?\n\nEsta aÃ§Ã£o nÃ£o pode ser desfeita!');
}
document.addEventListener('DOMContentLoaded', function() {
    const alertaSucesso = document.querySelector('.alert-success');
    if (alertaSucesso) {
        setTimeout(function() {
            alertaSucesso.style.transition = 'opacity 0.5s ease';
            alertaSucesso.style.opacity = '0';
            setTimeout(function() {
                alertaSucesso.style.display = 'none';
            }, 500);
        }, 5000);
    }
    document.body.style.opacity = '0';
    setTimeout(function() {
        document.body.style.transition = 'opacity 0.3s ease';
        document.body.style.opacity = '1';
    }, 100);
});
document.addEventListener('DOMContentLoaded', function() {
    const camposTexto = document.querySelectorAll('input[type="text"], textarea');
    camposTexto.forEach(function(campo) {
        campo.addEventListener('blur', function() {
            this.value = this.value.trim();
        });
    });
});
document.addEventListener('DOMContentLoaded', function() {
    const camposObrigatorios = document.querySelectorAll('input[required], select[required], textarea[required]');
    camposObrigatorios.forEach(function(campo) {
        campo.addEventListener('blur', function() {
            if (this.value.trim() === '') {
                this.style.borderColor = '#dc3545';
            } else {
                this.style.borderColor = '#28a745';
            }
        });
        campo.addEventListener('input', function() {
            if (this.value.trim() !== '') {
                this.style.borderColor = '#28a745';
            }
        });
    });
});
