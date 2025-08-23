# import numpy as np
# matriz = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
# resultado = matriz*2
# print(resultado)


# import pandas as pd
# from pandas import Series, DataFrame
# obj = Series([4,7,-5,3], index=['bunda', 'pinto', 'merda', 'cocô'])
# obj.name = 'Corpo'
# print(obj)


# import pandas as pd
# from pandas import DataFrame
# data = {'Estado':['SC', 'SP', 'PR', 'GO', 'BA', 'MG'],
#         'Ano':[2020, 2021, 2022, 2023, 2024, 2025],
#         'População':[1.5, 10, 0.1, 0.4, 0.001, 1]}
# frame = DataFrame(data)
# print(frame)

# frame2 = DataFrame(data, columns=['Ano', 'Estado', 'População']).sort_values('População', ascending=False,)
# frame2.index=['1º', '2º', '3º', '4º', '5º', '6º']
# print(frame2)
# frame2.describe()
# frame2.dtypes
# frame2.index
# frame2.columns
# frame2.values
# frame2['Ano']
# frame2.Ano # Case sensitive
# frame2[:2]
# print(frame2.set_index('Ano')) # Irá tirar o index
# frame2.set_index('Ano')