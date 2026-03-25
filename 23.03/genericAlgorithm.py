import numpy as np
import pandas as pd 
import random as rd 
import matplotlib.pyplot as plt
from random import randint

##############################################
# Populando as listas.

n = 10
numero_itens = np.arange(1, n+1)

pesos = [2.4, 1.7, 0.7, 2.1, 1.5, 2.2, 0.9, 1.6, 0.5, 1.1]
valores = [2000, 1450, 3400, 1900, 1300, 1000, 600, 1300, 400, 900]

nomes = ['Smartphone Samsung Galaxy S21',
        'Notebook Dell Inspiron 15',
        'Fone de ouvido Bluetooth JBL',
        'Smartwatch Samsung Galaxy Watch 3',
        'Tablet Apple iPad 10.2',
        'Câmera Digital Canon EOS Rebel T7',
        'Mouse Gamer Logitech G Pro',
        'Teclado Mecânico Redragon Kumara',
        'Caixa de Som Bluetooth JBL GO',
        'Smartband Xiaomi Mi Band 6']

max_peso_mochila = 7

# for i in range (numeto_itens.shape[0]):
    # print(f'Item {i+1}: {nomes[i]} - Peso: {pesos[i]} kg - Valor: R${valores[i]}')

##############################################
# Configurando a população

solucao_por_populacao = 8
tamanho_populacao = (solucao_por_populacao, numero_itens.shape[0])

# print('Tamanho da população = ', tamanho_populacao)
# print('Número de indivíduos (solução) = ', tamanho_populacao[0])
# print('Número de itens (genes) = ', tamanho_populacao[1])

##############################################
# Iniciando a população

n_geracoes = 10
# Criando a população onde somente um item será levado por individuo
populacao_inicial = np.eye(tamanho_populacao[0], tamanho_populacao[1], k=0)

populacao_inicial = populacao_inicial.astype(int)

# print('População inicial: \n', populacao_inicial)

##############################################
# Criando função para calcular FITNESS

def cal_fitness(peso, valor, populacao, max_peso_mochila):
  fitness = np.zeros(populacao.shape[0])

  # Percorre cada individuo
  for i in range(populacao.shape[0]):
    S1 = np.sum(populacao[i] * valor)
    S2 = np.sum(populacao[i] * peso)
    if S2 > max_peso_mochila:
      fitness[i] = 0
    else:
      fitness[i] = S1
  return fitness.astype(float)

##############################################
# Criando função para SELEÇÃO (Roleta)

def selecao_roleta(fitness, numero_pais, populacao):
  max_fitness = np.sum(fitness)
  probabilidades = fitness / max_fitness
  selecionados = np.random.choice(len(populacao), size=numero_pais, p=probabilidades)
  
  return populacao[selecionados]