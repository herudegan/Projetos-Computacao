import pandas as pd
from pandas import Series

obj = Series(['27°', '23°', '17°', '0°', '-5°', '10°', '15°']
             , index=['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'])
print(obj)