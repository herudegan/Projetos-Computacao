import * as React from 'react';
import { Text, View, ScrollView } from 'react-native';
import { useNavigation, NavigationContainer } from '@react-navigation/native';
import { Button } from '@react-navigation/elements';
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';
import { SafeAreaView } from 'react-native-safe-area-context';

function HomeScreen() {
  const navigation = useNavigation();

  return (
    <ScrollView contentContainerStyle={{ flexGrow: 1 }}>
      <View
        style={{
          flex: 1,
          padding: 24,
          alignItems: 'center',
          justifyContent: 'center',
          backgroundColor: '#F4F6FB',
        }}
      >
        <View
          style={{
            width: '100%',
            padding: 24,
            borderRadius: 16,
            backgroundColor: '#FFFFFF',
            shadowColor: '#000',
            shadowOffset: { width: 0, height: 2 },
            shadowOpacity: 0.1,
            shadowRadius: 6,
            elevation: 3,
          }}
        >
          <Text
            style={{
              fontSize: 22,
              fontWeight: '700',
              marginBottom: 8,
              textAlign: 'center',
            }}
          >
            Home Screen
          </Text>

          <Text
            style={{
              fontSize: 14,
              color: '#555',
              textAlign: 'center',
              marginBottom: 16,
            }}
          >
            Aqui você pode colocar um resumo geral, textos de apresentação
            e qualquer informação de encheção de linguiça que quiser só para
            deixar a tela mais completa.
          </Text>

          <View style={{ marginBottom: 16 }}>
            <Text style={{ fontWeight: '600', marginBottom: 8 }}>
              Destaques do dia
            </Text>
            <Text style={{ color: '#666', marginBottom: 4 }}>
              • Item 1: alguma informação importante ou não tão importante.
            </Text>
            <Text style={{ color: '#666', marginBottom: 4 }}>
              • Item 2: mais um texto só para ocupar espaço na tela.
            </Text>
            <Text style={{ color: '#666' }}>
              • Item 3: detalhes adicionais, descrição, etc.
            </Text>
          </View>

          <View style={{ alignItems: 'center' }}>
            <Button onPress={() => navigation.navigate('Profile')}>
              Go to Profile
            </Button>
          </View>
        </View>
      </View>
    </ScrollView>
  );
}

function ProfileScreen() {
  const navigation = useNavigation();

  return (
    <ScrollView contentContainerStyle={{ flexGrow: 1 }}>
      <View
        style={{
          flex: 1,
          padding: 24,
          alignItems: 'center',
          justifyContent: 'center',
          backgroundColor: '#F4F6FB',
        }}
      >
        <View
          style={{
            width: '100%',
            padding: 24,
            borderRadius: 16,
            backgroundColor: '#FFFFFF',
            shadowColor: '#000',
            shadowOffset: { width: 0, height: 2 },
            shadowOpacity: 0.1,
            shadowRadius: 6,
            elevation: 3,
          }}
        >
          <Text
            style={{
              fontSize: 22,
              fontWeight: '700',
              marginBottom: 8,
              textAlign: 'center',
            }}
          >
            Profile Screen
          </Text>

          <Text
            style={{
              fontSize: 14,
              color: '#555',
              textAlign: 'center',
              marginBottom: 16,
            }}
          >
            Espaço para descrição do usuário, informações básicas, biografia
            e qualquer outro texto de preenchimento que você quiser
            adicionar.
          </Text>

          <View style={{ marginBottom: 16 }}>
            <Text style={{ fontWeight: '600', marginBottom: 8 }}>
              Informações gerais
            </Text>
            <Text style={{ color: '#666', marginBottom: 4 }}>
              • Nome completo: Fulano de Tal.
            </Text>
            <Text style={{ color: '#666', marginBottom: 4 }}>
              • Função: usuário de teste da aplicação.
            </Text>
            <Text style={{ color: '#666' }}>
              • Observações: campo livre para anotações e comentários.
            </Text>
          </View>

          <View style={{ alignItems: 'center' }}>
            <Button onPress={() => navigation.navigate('Home')}>
              Go to Home
            </Button>
          </View>
        </View>
      </View>
    </ScrollView>
  );
}

const Tab = createMaterialTopTabNavigator();

function MyTabs() {
  return (
    <Tab.Navigator
      screenOptions={{
        tabBarStyle: {
          backgroundColor: '#F4F6FB',
          elevation: 0,
          shadowOpacity: 0,
        },
        tabBarIndicatorStyle: {
          backgroundColor: '#3B82F6',
          height: 3,
        },
        tabBarLabelStyle: {
          fontWeight: '600',
        },
      }}
    >
      <Tab.Screen name="Home" component={HomeScreen} />
      <Tab.Screen name="Profile" component={ProfileScreen} />
    </Tab.Navigator>
  );
}

export default function App() {
  return (
    <SafeAreaView style={{ flex: 1, backgroundColor: '#F4F6FB' }}>
      <NavigationContainer>
        <MyTabs />
      </NavigationContainer>
    </SafeAreaView>
  );
}