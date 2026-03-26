import { StatusBar } from 'expo-status-bar';
import { useState } from 'react';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';

export default function App() {
  const [displayValue, setDisplayValue] = useState('0');
  const [firstValue, setFirstValue] = useState(null);
  const [operator, setOperator] = useState(null);
  const [waitingForSecond, setWaitingForSecond] = useState(false);

  const handleClear = () => {
    setDisplayValue('0');
    setFirstValue(null);
    setOperator(null);
    setWaitingForSecond(false);
  };

  const handleDigit = (digit) => {
    if (waitingForSecond) {
      setDisplayValue(digit);
      setWaitingForSecond(false);
      return;
    }

    setDisplayValue((prev) => (prev === '0' ? digit : prev + digit));
  };

  const calculate = (a, op, b) => {
    switch (op) {
      case '+':
        return a + b;
      case '-':
        return a - b;
      case '/':
        if (b === 0) return NaN;
        return a / b;
      default:
        return b;
    }
  };

  const handleOperator = (op) => {
    const currentValue = parseFloat(displayValue);

    if (firstValue !== null && operator && !waitingForSecond) {
      const result = calculate(firstValue, operator, currentValue);
      const formatted = isNaN(result) || !isFinite(result) ? 'Erro' : String(result);
      setDisplayValue(formatted);
      setFirstValue(isNaN(result) || !isFinite(result) ? null : result);
    } else {
      setFirstValue(currentValue);
    }

    setOperator(op);
    setWaitingForSecond(true);
  };

  const handleEquals = () => {
    if (firstValue === null || !operator) return;

    const secondValue = parseFloat(displayValue);
    const result = calculate(firstValue, operator, secondValue);
    const formatted = isNaN(result) || !isFinite(result) ? 'Erro' : String(result);

    setDisplayValue(formatted);
    setFirstValue(null);
    setOperator(null);
    setWaitingForSecond(false);
  };

  return (
    <View style={styles.container}>
      <StatusBar style="auto" />
      <View style={styles.display}>
        <Text style={styles.displayLabel}>Calculadora</Text>
        <Text style={styles.displayText}>{displayValue}</Text>
      </View>

      <View style={styles.keypad}>
        <View style={styles.row}>
          <TouchableOpacity style={[styles.key, styles.keyFunction]} onPress={handleClear}>
            <Text style={[styles.keyText, styles.keyTextFunction]}>C</Text>
          </TouchableOpacity>
          <View style={styles.keyPlaceholder} />
          <View style={styles.keyPlaceholder} />
        </View>

        <View style={styles.row}>
          <TouchableOpacity style={styles.key} onPress={() => handleDigit('7')}>
            <Text style={styles.keyText}>7</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.key} onPress={() => handleDigit('8')}>
            <Text style={styles.keyText}>8</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.key} onPress={() => handleDigit('9')}>
            <Text style={styles.keyText}>9</Text>
          </TouchableOpacity>
          <TouchableOpacity style={[styles.key, styles.keyOperator]} onPress={() => handleOperator('/')}>
            <Text style={styles.keyText}>/</Text>
          </TouchableOpacity>
        </View>

        <View style={styles.row}>
          <TouchableOpacity style={styles.key} onPress={() => handleDigit('4')}>
            <Text style={styles.keyText}>4</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.key} onPress={() => handleDigit('5')}>
            <Text style={styles.keyText}>5</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.key} onPress={() => handleDigit('6')}>
            <Text style={styles.keyText}>6</Text>
          </TouchableOpacity>
          <TouchableOpacity style={[styles.key, styles.keyOperator]} onPress={() => handleOperator('-')}>
            <Text style={styles.keyText}>-</Text>
          </TouchableOpacity>
        </View>

        <View style={styles.row}>
          <TouchableOpacity style={styles.key} onPress={() => handleDigit('1')}>
            <Text style={styles.keyText}>1</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.key} onPress={() => handleDigit('2')}>
            <Text style={styles.keyText}>2</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.key} onPress={() => handleDigit('3')}>
            <Text style={styles.keyText}>3</Text>
          </TouchableOpacity>
          <TouchableOpacity style={[styles.key, styles.keyOperator]} onPress={() => handleOperator('+')}>
            <Text style={styles.keyText}>+</Text>
          </TouchableOpacity>
        </View>

        <View style={styles.rowBottom}>
          <TouchableOpacity style={[styles.key, styles.keyZeroWide]} onPress={() => handleDigit('0')}>
            <Text style={styles.keyText}>0</Text>
          </TouchableOpacity>
          <TouchableOpacity style={[styles.key, styles.keyEquals]} onPress={handleEquals}>
            <Text style={styles.keyText}>=</Text>
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f2f3f7',
    justifyContent: 'flex-end',
    paddingHorizontal: 16,
    paddingBottom: 72,
  },
  display: {
    backgroundColor: '#ffffff',
    paddingHorizontal: 20,
    paddingVertical: 24,
    borderRadius: 16,
    minHeight: 110,
    alignItems: 'flex-end',
    justifyContent: 'center',
    marginBottom: 24,
    elevation: 2,
  },
  displayLabel: {
    fontSize: 14,
    color: '#888',
    marginBottom: 6,
  },
  displayText: {
    fontSize: 42,
    color: '#000000',
    fontWeight: '400',
  },
  keypad: {
    width: '100%',
    paddingTop: 8,
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 12,
  },
  rowBottom: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: 4,
  },
  key: {
    flex: 1,
    height: 64,
    marginHorizontal: 4,
    borderRadius: 16,
    backgroundColor: '#ffffff',
    justifyContent: 'center',
    alignItems: 'center',
    elevation: 1,
  },
  keyZero: {
    flex: 1,
  },
  keyZeroWide: {
    flex: 3,
  },
  keyOperator: {
    backgroundColor: '#ff9f0a',
  },
  keyEquals: {
    backgroundColor: '#ff9f0a',
  },
  keyFunction: {
    backgroundColor: '#d3d7e0',
  },
  keyPlaceholder: {
    flex: 1,
    marginHorizontal: 4,
  },
  keyText: {
    fontSize: 22,
    color: '#000000',
    fontWeight: '500',
  },
  keyTextFunction: {
    color: '#000000',
  },
});
