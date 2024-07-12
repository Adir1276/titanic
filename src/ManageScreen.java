import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class ManageScreen extends JPanel {
    private JComboBox<String> classComboBox, sexComboBox, embarkedComboBox, groupComboBox;
    private JButton filterButton, statsButton, groupButton;
    private JTextField minPassId, maxPassId, nameField, sibField, parentField, ticketField, minPriceField, maxPriceField, cabinField;
    private List<Passenger> passengers;
    private Map<String, Float> groupMap;
    private int exportCount = 1;
    private String drawString = "";
    private String groupString = "";


    public ManageScreen(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file
        if (file.exists()) {
            parseCsv(file);
            this.setLayout(null);
            this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);
            JLabel survivedLabel = new JLabel("Passenger Class: ");
            survivedLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y, 110, Constants.LABEL_HEIGHT);
            this.add(survivedLabel);
            this.classComboBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
            this.classComboBox.setBounds(survivedLabel.getX() + survivedLabel.getWidth() + 1, survivedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.classComboBox);
            JLabel passengerIdLabel = new JLabel("Passenger Id: ");
            passengerIdLabel.setBounds(classComboBox.getX() + classComboBox.getWidth() + 20, classComboBox.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(passengerIdLabel);
            this.minPassId = new JTextField();
            this.maxPassId = new JTextField();
            minPassId.setBounds(passengerIdLabel.getX() + passengerIdLabel.getWidth() + 1, passengerIdLabel.getY(), Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT);
            maxPassId.setBounds(minPassId.getX() + minPassId.getWidth() + 1, minPassId.getY(), Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT);
            this.add(minPassId);
            this.add(maxPassId);
            JLabel nameLabel = new JLabel("Name: ");
            nameLabel.setBounds(maxPassId.getX() + maxPassId.getWidth() + 1, maxPassId.getY(), 50, Constants.LABEL_HEIGHT);
            this.add(nameLabel);
            this.nameField = new JTextField();
            nameField.setBounds(nameLabel.getX() + nameLabel.getWidth() + 1, nameLabel.getY(), 100, Constants.FIELD_HEIGHT);
            this.add(nameField);
            JLabel sexLabel = new JLabel("Sex: ");
            sexLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, survivedLabel.getY() + survivedLabel.getHeight(), 30, Constants.LABEL_HEIGHT);
            this.add(sexLabel);
            this.sexComboBox = new JComboBox<>(Constants.SEX_OPTIONS);
            this.sexComboBox.setBounds(sexLabel.getX() + sexLabel.getWidth() + 1, sexLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.sexComboBox);
            JLabel sibSpLabel = new JLabel("sibSp: ");
            sibSpLabel.setBounds(sexComboBox.getX() + sexComboBox.getWidth() + 20, sexComboBox.getY(), 50, Constants.LABEL_HEIGHT);
            this.add(sibSpLabel);
            this.sibField = new JTextField();
            sibField.setBounds(sibSpLabel.getX() + sibSpLabel.getWidth() + 1, sibSpLabel.getY(), Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT);
            this.add(sibField);
            JLabel parchLabel = new JLabel("Parch: ");
            parchLabel.setBounds(sibField.getX() + sibField.getWidth() + 20, sibField.getY(), 50, Constants.LABEL_HEIGHT);
            this.add(parchLabel);
            this.parentField = new JTextField();
            parentField.setBounds(parchLabel.getX() + parchLabel.getWidth() + 1, parchLabel.getY(), Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT);
            this.add(parentField);
            JLabel ticketLabel = new JLabel("Ticket: ");
            ticketLabel.setBounds(parentField.getX() + parentField.getWidth() + 20, parentField.getY(), 60, Constants.LABEL_HEIGHT);
            this.add(ticketLabel);
            this.ticketField = new JTextField();
            ticketField.setBounds(ticketLabel.getX() + ticketLabel.getWidth() + 1, ticketLabel.getY(), Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT);
            this.add(ticketField);
            JLabel priceLabel = new JLabel("Price: ");
            priceLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, sexLabel.getY() + sexLabel.getHeight(), 60, Constants.LABEL_HEIGHT);
            this.add(priceLabel);
            this.minPriceField = new JTextField();
            minPriceField.setBounds(priceLabel.getX() + priceLabel.getWidth() + 1, priceLabel.getY(), Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT);
            this.add(minPriceField);
            this.maxPriceField = new JTextField();
            maxPriceField.setBounds(minPriceField.getX() + minPriceField.getWidth() + 1, minPriceField.getY(), Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT);
            this.add(maxPriceField);
            JLabel cabinLabel = new JLabel("Cabin: ");
            cabinLabel.setBounds(maxPriceField.getX() + maxPriceField.getWidth() + 1, maxPriceField.getY(), Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT);
            this.add(cabinLabel);
            this.cabinField = new JTextField();
            cabinField.setBounds(cabinLabel.getX() + cabinLabel.getWidth() + 1, cabinLabel.getY(), Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT);
            this.add(cabinField);
            JLabel embarkedLabel = new JLabel("Embarked: ");
            embarkedLabel.setBounds(cabinField.getX() + cabinField.getWidth() + 1, cabinField.getY(), 80, Constants.FIELD_HEIGHT);
            this.add(embarkedLabel);
            this.embarkedComboBox = new JComboBox<>(Constants.EMBARKED_OPTIONS);
            this.embarkedComboBox.setBounds(embarkedLabel.getX() + embarkedLabel.getWidth() + 1, embarkedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.embarkedComboBox);
            filterButton = new JButton("Filter");
            filterButton.setBounds(embarkedComboBox.getX() + embarkedComboBox.getWidth() + 50, embarkedComboBox.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(filterButton);
            this.filterButton.addActionListener((e) -> {
                filterPassengers();
            });

            statsButton = new JButton("Stats");
            statsButton.setBounds(filterButton.getX(), filterButton.getY() + 50, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(statsButton);
            this.statsButton.addActionListener((e) -> {
                exportStats();
            });

            JLabel groupLabel = new JLabel("Group By");
            groupLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, statsButton.getY() + statsButton.getHeight(), 60, Constants.LABEL_HEIGHT);
            this.add(groupLabel);
            this.groupComboBox = new JComboBox<>(Constants.GROUP_OPTIONS);
            this.groupComboBox.setBounds(groupLabel.getX() + groupLabel.getWidth() + 1, groupLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.groupComboBox);
            groupButton = new JButton("Group");
            groupButton.setBounds(groupComboBox.getX() +groupComboBox.getWidth() + 20 , groupComboBox.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(groupButton);
            this.groupButton.addActionListener((e) -> {
                this.groupMap = groupStats();
                this.groupString = mapToString(this.groupMap);
                repaint();
            });

        }
    }

    public static String mapToString(Map<String, Float> map) {
        String result = "";
        for (Map.Entry<String, Float> entry : map.entrySet()) {
            result += entry.getKey() + "=" + entry.getValue() + "  ";
        }
        return result;
    }

    private Map<String, Float> groupStats(){
        switch ((String) groupComboBox.getSelectedItem()) {
            case "Class":
                return groupByClass();
            case "Sex":
                return groupBySex();
            case "Ports":
                return groupByPort();
        }
        return null;
    }

    private Map<String, Float> sortMap(Map<String, Float> oldMap) {
        List<Map.Entry<String, Float>> list = new ArrayList<>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {
            @Override
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        Map<String, Float> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Float> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    private Map<String, Float> groupByPort(){
        Map<String, Float> map = new HashMap<>();
        List<Passenger> S = new ArrayList<>();
        List<Passenger> C = new ArrayList<>();
        List<Passenger> Q = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getEmbarked().equals("S"))
                S.add(passenger);
            else if (passenger.getEmbarked().equals("C"))
                C.add(passenger);
            else if (passenger.getEmbarked().equals("Q"))
                Q.add(passenger);
        }
        map.put("S Port", 100 * (float) S.size() / passengers.size());
        map.put("C Port", 100 * (float) C.size() / passengers.size());
        map.put("Q Port", 100 * (float) Q.size() / passengers.size());
        return sortMap(map);
    }

    private Map<String, Float> groupBySex(){
        Map<String, Float> map = new HashMap<>();
        List<Passenger> males = new ArrayList<>();
        List<Passenger> females = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getSex().equals("male"))
                males.add(passenger);
            else if (passenger.getSex().equals("female"))
                females.add(passenger);
        }
        map.put("Males", 100 * (float) males.size() / passengers.size());
        map.put("Females", 100 * (float) females.size() / passengers.size());
        return sortMap(map);
    }

    private Map<String, Float> groupByClass(){
        Map<String, Float> map = new HashMap<>();
        List<Passenger> firstClass = new ArrayList<>();
        List<Passenger> secondClass = new ArrayList<>();
        List<Passenger> thirdClass = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getpClass() == 1)
                firstClass.add(passenger);
            else if (passenger.getpClass() == 2)
                secondClass.add(passenger);
            else if (passenger.getpClass() == 3)
                thirdClass.add(passenger);
        }
        map.put("First Class", 100 * (float) firstClass.size() / passengers.size());
        map.put("Second Class", 100 * (float) secondClass.size() / passengers.size());
        map.put("Third Class", 100 * (float) thirdClass.size() / passengers.size());
        return sortMap(map);
    }

    private void filterPassengers(){
        List<Passenger> filteredPassengers;
        filteredPassengers = filterPassengersByClass(this.passengers);
        filteredPassengers = filterPassengersByPassengerId(filteredPassengers);
        filteredPassengers = filterPassengersByName(filteredPassengers);
        filteredPassengers = filterPassengersBySex(filteredPassengers);
        filteredPassengers = filterPassengersBySiblings(filteredPassengers);
        filteredPassengers = filterPassengersByParents(filteredPassengers);
        filteredPassengers = filterPassengersByTicket(filteredPassengers);
        filteredPassengers = filterPassengersByTicketPrice(filteredPassengers);
        filteredPassengers = filterPassengersByCabin(filteredPassengers);
        filteredPassengers = filterPassengersByEmbarked(filteredPassengers);
        Collections.sort(filteredPassengers, (a, b) -> a.getFormattedName().compareToIgnoreCase(b.getFormattedName()));
        int survived = getAlivePassengerCount(filteredPassengers);
        this.drawString = "Total Rows: " + filteredPassengers.size() + " (" + survived +
                " survived, " + (filteredPassengers.size() - survived) + " did not) ";
        repaint();
        exportToCSV(filteredPassengers);
    }

    private void exportStats(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.PATH_TO_STATS_FILE))) {
            String content = getClassStats() + getSexStats() + getAgeStats() + getRelativeStats() + getTicketPriceStats()
                    + getEmbarkedStats();
            bw.write(content);
        } catch (IOException e) {

        }
    }

    private String getClassStats(){
        List<Passenger> firstClass = new ArrayList<>();
        List<Passenger> secondClass = new ArrayList<>();
        List<Passenger> thirdClass = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getpClass() == 1)
                firstClass.add(passenger);
            else if (passenger.getpClass() == 2)
                secondClass.add(passenger);
            else if (passenger.getpClass() == 3)
                thirdClass.add(passenger);
        }
        float firstSurvived = 100 * (float) getAlivePassengerCount(firstClass) / firstClass.size();
        float secondSurvived = 100 * (float) getAlivePassengerCount(secondClass) / secondClass.size();
        float thirdSurvived = 100 * (float) getAlivePassengerCount(thirdClass) / thirdClass.size();
        return "\n\n---Class Stats---\nfirst class survivors = " + firstSurvived + "\nsecond class survivors = " + secondSurvived +
                "\nthird class survivors = " + thirdSurvived;
    }

    private String getSexStats(){
        List<Passenger> males = new ArrayList<>();
        List<Passenger> females = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getSex().equals("male"))
                males.add(passenger);
            else if (passenger.getSex().equals("female"))
                females.add(passenger);
        }
        float malesSurvived = 100 * (float) getAlivePassengerCount(males) / males.size();
        float femalesSurvived = 100 * (float) getAlivePassengerCount(females) / females.size();
        return "\n\n---Sex Stats---\nmale survivors = " + malesSurvived + "\nfemale survivors = " + femalesSurvived;
    }

    private String getAgeStats(){
        List<Passenger> ages = new ArrayList<>();
        List<Passenger> ages2 = new ArrayList<>();
        List<Passenger> ages3 = new ArrayList<>();
        List<Passenger> ages4 = new ArrayList<>();
        List<Passenger> ages5 = new ArrayList<>();
        List<Passenger> others = new ArrayList<>();

        for (Passenger passenger : passengers) {
            if (passenger.getAge() < 0)
                continue; //unknown age
            else if (passenger.getAge() <= 10)
                ages.add(passenger);
            else if (passenger.getAge() <= 20)
                ages2.add(passenger);
            else if (passenger.getAge() <= 30)
                ages3.add(passenger);
            else if (passenger.getAge() <= 40)
                ages4.add(passenger);
            else if (passenger.getAge() <= 50)
                ages5.add(passenger);
            else
                others.add(passenger);
        }

        float age10Survived = 100 * (float) getAlivePassengerCount(ages) / ages.size();
        float age20Survived = 100 * (float) getAlivePassengerCount(ages2) / ages2.size();
        float age30Survived = 100 * (float) getAlivePassengerCount(ages3) / ages3.size();
        float age40Survived = 100 * (float) getAlivePassengerCount(ages4) / ages4.size();
        float age50Survived = 100 * (float) getAlivePassengerCount(ages5) / ages5.size();
        float othersSurvived = 100 * (float) getAlivePassengerCount(others) / others.size();

        return "\n\n---Age Stats---\nAge 0-10 survivors = " + age10Survived + "\nage 11-20 survivors = " + age20Survived +
               "\nAge 21-30 survivors = " + age30Survived + "\nage 31-40 survivors = " + age40Survived +
                "\nAge 41-50 survivors = " + age50Survived + "\nAge 51+ survivors = " + othersSurvived;

    }

    private String getRelativeStats(){
        List<Passenger> hasRelatives = new ArrayList<>();
        List<Passenger> noRelatives = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getParch() + passenger.getSibSp() > 0)
                hasRelatives.add(passenger);
            else
                noRelatives.add(passenger);
        }
        float hasRelativesSurvived = 100 * (float) getAlivePassengerCount(hasRelatives) / hasRelatives.size();
        float noRelativesSurvived = 100 * (float) getAlivePassengerCount(noRelatives) / noRelatives.size();
        return "\n\n---Relative Stats---\nHas relatives survivors = " + hasRelativesSurvived +
                "\nno relatives survivors = " + noRelativesSurvived;
    }

    private String getTicketPriceStats(){
        List<Passenger> sub10 = new ArrayList<>();
        List<Passenger> sub30 = new ArrayList<>();
        List<Passenger> others = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getFare() <= 10)
                sub10.add(passenger);
            else if (passenger.getFare() <= 30)
                sub30.add(passenger);
            else if (passenger.getFare() > 30)
                others.add(passenger);
        }

        float sub10Survived = 100 * (float) getAlivePassengerCount(sub10) / sub10.size();
        float sub30Survived = 100 * (float) getAlivePassengerCount(sub30) / sub30.size();
        float othersSurvived = 100 * (float) getAlivePassengerCount(others) / others.size();
        return "\n\n---Price Stats---\n0-10 pound ticket survivors = " + sub10Survived +
                "\n11-30 pound survivors = " + sub30Survived + "\n30+ pound survivors = " + othersSurvived;
    }

    private String getEmbarkedStats(){
        List<Passenger> S = new ArrayList<>();
        List<Passenger> C = new ArrayList<>();
        List<Passenger> Q = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getEmbarked().equals("S"))
                S.add(passenger);
            else if (passenger.getEmbarked().equals("C"))
                C.add(passenger);
            else if (passenger.getEmbarked().equals("Q"))
                Q.add(passenger);
        }

        float sSurvived = 100 * (float) getAlivePassengerCount(S) / S.size();
        float cSurvived = 100 * (float) getAlivePassengerCount(C) / C.size();
        float qSurvived = 100 * (float) getAlivePassengerCount(Q) / Q.size();
        return "\n\n---Port Stats---\nS port survivors = " + sSurvived +
                "\nC Port survivors = " + cSurvived + "\nQ Port survivors = " + qSurvived;
    }



    private void exportToCSV(List<Passenger> passengers) {
        String csvFile = "src/data/" + this.exportCount + ".csv";
        this.exportCount++;
        String[] headers = {"Passenger", "Survived", "Pclass", "Name", "Sex", "Age", "SibSp", "Parch", "Ticket", "Fare", "Cabin", "Embarked"};

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            writeLine(bw, headers);
            for (Passenger passenger : passengers) {
                String[] data = {String.valueOf(passenger.getPassengerId()), String.valueOf(passenger.getSurvived()),
                        String.valueOf(passenger.getpClass()), passenger.getFormattedName(), passenger.getSex(),
                        ((passenger.getAge() == -1) ? "" : String.valueOf(passenger.getAge())),
                        String.valueOf(passenger.getSibSp()), String.valueOf(passenger.getParch()), passenger.getTicket(),
                        String.valueOf(passenger.getFare()), passenger.getCabin(), passenger.getEmbarked()
                };
                writeLine(bw, data);
            }

        } catch (IOException e) {

        }
    }

    private static void writeLine(BufferedWriter bw, String[] values) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if (i < values.length - 1) {
                sb.append(",");
            }
        }
        bw.write(sb.toString());
        bw.newLine();
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString(drawString, 20, 125);
        g.drawString(groupString, 20, 200);
    }

    private List<Passenger> filterPassengersByClass(List<Passenger> passengers){
        int classChoice = 0;
        switch ((String) classComboBox.getSelectedItem()) {
            case "1st":
                classChoice = 1;
                break;
            case "2nd":
                classChoice = 2;
                break;
            case "3rd":
                classChoice = 3;
                break;
        }
        if (classChoice == 0)
            return passengers;

        List<Passenger> filteredPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getpClass() == classChoice)
                filteredPassengers.add(passenger);
        }
        return filteredPassengers;
    }

    private List<Passenger> filterPassengersByPassengerId(List<Passenger> passengers){
        int minId = minPassId.getText().isEmpty() ? 0 : Integer.parseInt(minPassId.getText());
        int maxId = maxPassId.getText().isEmpty() ? 999 : Integer.parseInt(maxPassId.getText());


        List<Passenger> filteredPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
                if (passenger.getPassengerId() <= maxId && minId <= passenger.getPassengerId())
                    filteredPassengers.add(passenger);
            }
        return filteredPassengers;
    }

    private List<Passenger> filterPassengersByName(List<Passenger> passengers){
        List<Passenger> filteredPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getFormattedName().contains(nameField.getText()))
                filteredPassengers.add(passenger);
        }
        return filteredPassengers;
    }

    private List<Passenger> filterPassengersBySex(List<Passenger> passengers){
        if (sexComboBox.getSelectedItem().equals("All"))
            return passengers;
        List<Passenger> filteredPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getSex().equals(sexComboBox.getSelectedItem()))
                filteredPassengers.add(passenger);
        }
        return filteredPassengers;
    }

    private List<Passenger> filterPassengersBySiblings(List<Passenger> passengers){
        if (sibField.getText().isEmpty())
            return passengers;

        List<Passenger> filteredPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getSibSp() == Integer.parseInt(sibField.getText()))
                filteredPassengers.add(passenger);
        }
        return filteredPassengers;
    }

    private List<Passenger> filterPassengersByParents(List<Passenger> passengers){
        if (parentField.getText().isEmpty())
            return passengers;

        List<Passenger> filteredPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getParch() == Integer.parseInt(parentField.getText()))
                filteredPassengers.add(passenger);
        }
        return filteredPassengers;
    }

    private List<Passenger> filterPassengersByTicket(List<Passenger> passengers){
        if (ticketField.getText().isEmpty())
            return passengers;

        List<Passenger> filteredPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getTicket().equals(ticketField.getText()))
                filteredPassengers.add(passenger);
        }
        return filteredPassengers;
    }

    private List<Passenger> filterPassengersByTicketPrice(List<Passenger> passengers){
        float minPrice = minPriceField.getText().isEmpty() ? 0 : Float.parseFloat(minPriceField.getText());
        float maxPrice = maxPriceField.getText().isEmpty() ? 9999 : Float.parseFloat(maxPriceField.getText());


        List<Passenger> filteredPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getFare() <= maxPrice && minPrice <= passenger.getFare())
                filteredPassengers.add(passenger);
        }
        return filteredPassengers;
    }

    private List<Passenger> filterPassengersByCabin(List<Passenger> passengers){
        if (cabinField.getText().isEmpty())
            return passengers;

        List<Passenger> filteredPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getCabin().equals(cabinField.getText()))
                filteredPassengers.add(passenger);
        }
        return filteredPassengers;
    }

    private List<Passenger> filterPassengersByEmbarked(List<Passenger> passengers){
        if (embarkedComboBox.getSelectedItem().equals("All"))
            return passengers;

        List<Passenger> filteredPassengers = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getEmbarked().equals(embarkedComboBox.getSelectedItem()))
                filteredPassengers.add(passenger);
        }
        return filteredPassengers;
    }

    private int getAlivePassengerCount(List<Passenger> passengers) {
        int count = 0;
        for (Passenger passenger : passengers) {
            if (passenger.getSurvived() == 1)
                count+=1;
        }
        return count;
    }

    private void parseCsv(File file){
        try (BufferedReader br = new BufferedReader(new FileReader(Constants.PATH_TO_DATA_FILE))) {
            String line;
            line = br.readLine();//skip first line
            this.passengers = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int passengerId = Integer.parseInt(values[0]);
                int survived = Integer.parseInt(values[1]);
                int pClass = Integer.parseInt(values[2]);
                String name = (values[3] + ", " + values[4]).replace("\"", "");
                String sex = values[5];
                float age = (values[6] != "") ? Float.parseFloat(values[6]) : -1;
                int sibSp = Integer.parseInt(values[7]);
                int parch = Integer.parseInt(values[8]);
                String ticket = values[9];
                float fare = Float.parseFloat(values[10]);
                String cabin = values[11];
                String embarked = values.length > 12 ? values[12] : "";
                Passenger passenger = new Passenger(passengerId, survived, pClass, name, sex, age, sibSp, parch, ticket, fare, cabin, embarked);
                passengers.add(passenger);
            }
        }
        catch (IOException e) {}
    }



}
