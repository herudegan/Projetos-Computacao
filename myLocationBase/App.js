import { useState, useEffect, useRef } from "react";
import { Alert, FlatList, StyleSheet, View } from "react-native";
import {
  Appbar,
  Button,
  List,
  PaperProvider,
  Switch,
  Text,
  MD3DarkTheme,
  MD3LightTheme as DefaultTheme,
} from "react-native-paper";
import myColors from "./assets/colors.json";
import myColorsDark from "./assets/colorsDark.json";
import AsyncStorage from "@react-native-async-storage/async-storage";
import * as Location from "expo-location";
import * as SQLite from "expo-sqlite";

export default function App() {
  const [isSwitchOn, setIsSwitchOn] = useState(false); // variável para controle do darkMode
  const [isDarkModeLoaded, setIsDarkModeLoaded] = useState(false);
  const [isLoading, setIsLoading] = useState(false); // variável para controle do loading do button
  const [locations, setLocations] = useState([]); // variável para armazenar as localizações
  const dbRef = useRef(null);

  // Carrega tema default da lib RN PAPER com customização das cores. Para customizar o tema, veja:
  // https://callstack.github.io/react-native-paper/docs/guides/theming/#creating-dynamic-theme-colors
  const [theme, setTheme] = useState({
    ...DefaultTheme,
    myOwnProperty: true,
    colors: myColors.colors,
  });

  // load darkMode from AsyncStorage
  async function loadDarkMode() {
    try {
      const value = await AsyncStorage.getItem("@darkMode");
      if (value !== null) {
        setIsSwitchOn(value === "true");
      }
    } catch (e) {
      console.error("Error loading dark mode:", e);
    } finally {
      setIsDarkModeLoaded(true);
    }
  }

  // darkMode switch event
  async function onToggleSwitch() {
    setIsSwitchOn((previousValue) => !previousValue);
  }

  async function initializeDatabase() {
    const db = await SQLite.openDatabaseAsync("locations.db");
    await db.execAsync(`
      CREATE TABLE IF NOT EXISTS locations (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        latitude REAL NOT NULL,
        longitude REAL NOT NULL,
        created_at TEXT NOT NULL
      );
    `);

    dbRef.current = db;
  }

  async function insertLocation(latitude, longitude) {
    if (!dbRef.current) {
      return;
    }

    await dbRef.current.runAsync(
      "INSERT INTO locations (latitude, longitude, created_at) VALUES (?, ?, ?)",
      latitude,
      longitude,
      new Date().toISOString()
    );
  }

  // get location (botão capturar localização)
  async function getLocation() {
    setIsLoading(true);

    try {
      const { status } = await Location.requestForegroundPermissionsAsync();
      if (status !== "granted") {
        Alert.alert(
          "Permissão necessária",
          "Permita o acesso à localização para capturar as coordenadas."
        );
        return;
      }

      const position = await Location.getCurrentPositionAsync({
        accuracy: Location.Accuracy.Balanced,
      });
      const { latitude, longitude } = position.coords;

      await insertLocation(latitude, longitude);
      await loadLocations();
    } catch (e) {
      console.error("Error getting location:", e);
      Alert.alert("Erro", "Não foi possível capturar a localização.");
    } finally {
      setIsLoading(false);
    }
  }

  // load locations from db sqlite - faz a leitura das localizações salvas no banco de dados
  async function loadLocations() {
    if (!dbRef.current) {
      return;
    }

    setIsLoading(true);

    try {
      const savedLocations = await dbRef.current.getAllAsync(
        "SELECT id, latitude, longitude, created_at FROM locations ORDER BY id DESC"
      );

      setLocations(savedLocations);
    } catch (e) {
      console.error("Error loading locations:", e);
    } finally {
      setIsLoading(false);
    }
  }

  // Use Effect para carregar o darkMode e as localizações salvas no banco de dados
  // É executado apenas uma vez, quando o componente é montado
  useEffect(() => {
    async function initializeApp() {
      await loadDarkMode();
      await initializeDatabase();
      await loadLocations();
    }

    initializeApp();
  }, []);

  // Efetiva a alteração do tema dark/light quando a variável isSwitchOn é alterada
  // É executado sempre que a variável isSwitchOn é alterada
  useEffect(() => {
    if (!isDarkModeLoaded) {
      return;
    }

    async function saveDarkMode() {
      try {
        await AsyncStorage.setItem("@darkMode", isSwitchOn.toString());
      } catch (e) {
        console.error("Error saving dark mode:", e);
      }
    }

    saveDarkMode();

    if (isSwitchOn) {
      setTheme((previousTheme) => ({
        ...previousTheme,
        ...MD3DarkTheme,
        colors: myColorsDark.colors,
      }));
    } else {
      setTheme((previousTheme) => ({
        ...previousTheme,
        ...DefaultTheme,
        colors: myColors.colors,
      }));
    }
  }, [isSwitchOn, isDarkModeLoaded]);

  return (
    <PaperProvider theme={theme}>
      <Appbar.Header>
        <Appbar.Content title="My Location BASE" />
      </Appbar.Header>
      <View style={{ backgroundColor: theme.colors.background }}>
        <View style={styles.containerDarkMode}>
          <Text>Dark Mode</Text>
          <Switch value={isSwitchOn} onValueChange={onToggleSwitch} />
        </View>
        <Button
          style={styles.containerButton}
          icon="map"
          mode="contained"
          loading={isLoading}
          onPress={() => getLocation()}
        >
          Capturar localização
        </Button>

        <FlatList
          style={styles.containerList}
          data={locations}
          keyExtractor={(item) => item.id.toString()}
          renderItem={({ item }) => (
            <List.Item
              title={`Localização ${item.id}`}
              description={`Latitude: ${item.latitude} | Longitude: ${item.longitude}`}
            ></List.Item>
          )}
        ></FlatList>
      </View>
    </PaperProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  containerDarkMode: {
    margin: 10,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
  },
  containerButton: {
    margin: 10,
  },
  containerList: {
    margin: 10,
    height: "100%",
  },
});
