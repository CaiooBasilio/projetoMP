create database db_mp;
use db_mp;

create table categoria(
	id int primary key auto_increment,
    nomeDaCategoria varchar(255) not null,
    local varchar(255) not null,
    linha int
);
 
create table itensDoEstoque(
	id int primary key auto_increment,
    nomeDoItem varchar(255) not null,
    descricaoItem varchar(255) not null,
    categoriaDoItem int not null,
    quantidade int not null,
    noConcerto boolean,  
    
    foreign key(categoriaDoItem) references categoria(id)
);

create table funcionarios(
	id int primary key auto_increment,
    nomeFuncionario varchar(255) not null,
    contato varchar (255) not null,
    cargo varchar(25) not null
);

create table retiradas(
	id int primary key auto_increment,
    idFuncionario int not null,
    idItem int not null,
    quantidadePega int not null,
    dataPega date not null,
    dataDevolucao char(10),
    
    foreign key (idFuncionario) references funcionarios(id),
	foreign key (idItem) references itensDoEstoque(id)
);


# inserts gerados pela IA

INSERT INTO categoria (nomeDaCategoria, local, linha) VALUES
('Parafusos', 'Prateleira A', 1),
('Porcas', 'Prateleira A', 2),
('Arruelas', 'Prateleira A', 3),
('Ferramentas Manuais', 'Armário 1', 1),
('Ferramentas Elétricas', 'Armário 2', 1),
('Soldagem', 'Área Solda', 1),
('Lixadeiras', 'Área Ferramentas', 2),
('EPIs', 'Armário Segurança', 1),
('Chapas de Ferro', 'Galpão', 1),
('Brocas', 'Armário 3', 1),
('Tintas', 'Prateleira B', 1),
('Cabos', 'Armário 4', 1),
('Conectores', 'Armário 4', 2),
('Equipamentos Pesados', 'Galpão', 2);

INSERT INTO itensDoEstoque 
(nomeDoItem, descricaoItem, categoriaDoItem, quantidade, noConcerto) VALUES
('Parafuso Sextavado', 'Parafuso aço carbono', 1, 500, 0),
('Parafuso Allen', 'Parafuso com encaixe allen', 1, 300, 0),
('Porca Sextavada', 'Porca padrão aço', 2, 600, 0),
('Arruela Lisa', 'Arruela simples', 3, 800, 0),
('Martelo', 'Martelo de aço', 4, 20, 0),
('Chave de Fenda', 'Chave média', 4, 35, 0),
('Furadeira', 'Furadeira elétrica 500W', 5, 10, 0),
('Esmerilhadeira', 'Esmerilhadeira 750W', 5, 8, 0),
('Máquina de Solda', 'Solda MIG', 6, 5, 0),
('Lixadeira Orbital', 'Lixadeira profissional', 7, 6, 0),
('Capacete', 'Capacete de segurança', 8, 40, 0),
('Luva de Proteção', 'Luva anti-corte', 8, 100, 0),
('Chapa Ferro 2mm', 'Chapa fina', 9, 50, 0),
('Chapa Ferro 5mm', 'Chapa média', 9, 30, 0),
('Broca 5mm', 'Broca para metal', 10, 60, 0),
('Broca 10mm', 'Broca grossa', 10, 40, 0),
('Tinta Azul', 'Tinta industrial', 11, 25, 0),
('Óleo Lubrificante', 'Lubrificante geral', 12, 70, 0),
('Cabo de Aço', 'Cabo resistente', 13, 15, 0),
('Conector Elétrico', 'Conector padrão', 14, 120, 0);

INSERT INTO funcionarios (nomeFuncionario, contato, cargo) VALUES
('João Silva', '11999999999', 'Soldador'),
('Carlos Souza', '11988888888', 'Operador'),
('Marcos Lima', '11977777777', 'Auxiliar'),
('Pedro Santos', '11966666666', 'Supervisor'),
('Lucas Ferreira', '11955555555', 'Almoxarife'),
('Rafael Costa', '11944444444', 'Soldador'),
('Bruno Alves', '11933333333', 'Mecânico'),
('Diego Rocha', '11922222222', 'Operador'),
('André Gomes', '11911111111', 'Auxiliar'),
('Felipe Martins', '11900000000', 'Supervisor');

INSERT INTO retiradas 
(idFuncionario, idItem, quantidadePega, dataPega, dataDevolucao) VALUES
(1,1,50,'2026-03-01','2026-03-02'),
(2,2,30,'2026-03-02',NULL),
(3,3,20,'2026-03-03','2026-03-05'),
(4,4,10,'2026-03-04',NULL),
(5,5,2,'2026-03-05','2026-03-06'),
(6,6,3,'2026-03-06',NULL),
(7,7,1,'2026-03-07',NULL),
(8,8,1,'2026-03-08',NULL),
(9,9,5,'2026-03-09','2026-03-10'),
(10,10,2,'2026-03-10',NULL),
(1,11,5,'2026-03-11',NULL),
(2,12,10,'2026-03-12',NULL),
(3,13,8,'2026-03-13',NULL),
(4,14,6,'2026-03-14',NULL),
(5,15,4,'2026-03-15',NULL),
(6,16,3,'2026-03-16',NULL),
(7,17,2,'2026-03-17',NULL),
(8,18,7,'2026-03-18',NULL),
(9,19,1,'2026-03-19',NULL),
(10,20,9,'2026-03-20',NULL);

show tables;
