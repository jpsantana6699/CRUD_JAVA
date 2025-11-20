/**
 * Script JavaScript para validação de formulários e interações do usuário
 * @author João Pedro Santana
 * @version 1.0
 */

/**
 * Valida o formulário antes do envio
 * @returns {boolean} true se válido, false caso contrário
 */
function validarFormulario() {
    // Captura os campos do formulário
    const titulo = document.getElementById('titulo');
    const autorDiretor = document.getElementById('autorDiretor');
    const anoLancamento = document.getElementById('anoLancamento');
    const genero = document.getElementById('genero');
    const tipoMidia = document.getElementById('tipoMidia');
    
    // Valida título
    if (!titulo || titulo.value.trim() === '') {
        alert('Por favor, preencha o título!');
        if (titulo) titulo.focus();
        return false;
    }
    
    if (titulo.value.trim().length < 2) {
        alert('O título deve ter pelo menos 2 caracteres!');
        titulo.focus();
        return false;
    }
    
    // Valida autor/diretor
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
    
    // Valida ano de lançamento
    if (!anoLancamento || anoLancamento.value === '') {
        alert('Por favor, preencha o ano de lançamento!');
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
    
    // Valida gênero
    if (!genero || genero.value.trim() === '') {
        alert('Por favor, preencha o gênero!');
        if (genero) genero.focus();
        return false;
    }
    
    // Valida tipo de mídia
    if (!tipoMidia || tipoMidia.value === '') {
        alert('Por favor, selecione o tipo de mídia!');
        if (tipoMidia) tipoMidia.focus();
        return false;
    }
    
    return true;
}

/**
 * Confirma a exclusão de um item
 * @returns {boolean} true se confirmado, false caso contrário
 */
function confirmarExclusao() {
    return confirm('Tem certeza que deseja excluir este item?\n\nEsta ação não pode ser desfeita!');
}

/**
 * Esconde automaticamente mensagens de sucesso após alguns segundos
 */
document.addEventListener('DOMContentLoaded', function() {
    // Auto-esconder alertas de sucesso após 5 segundos
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
    
    // Adiciona animação suave ao carregar a página
    document.body.style.opacity = '0';
    setTimeout(function() {
        document.body.style.transition = 'opacity 0.3s ease';
        document.body.style.opacity = '1';
    }, 100);
});

/**
 * Limpa espaços extras nos campos de texto ao sair do campo
 */
document.addEventListener('DOMContentLoaded', function() {
    const camposTexto = document.querySelectorAll('input[type="text"], textarea');
    
    camposTexto.forEach(function(campo) {
        campo.addEventListener('blur', function() {
            this.value = this.value.trim();
        });
    });
});

/**
 * Adiciona feedback visual ao focar em campos obrigatórios vazios
 */
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
