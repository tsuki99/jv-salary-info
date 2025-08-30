package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final String SPACE = " ";
    private static final String HYPHEN = " - ";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;
    private static final int INDEX_THREE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] splittedData;
        StringBuilder report = new StringBuilder("Report for period "
                + dateFrom + HYPHEN + dateTo + System.lineSeparator());

        for (int i = 0; i < names.length; i++) {

            int salary = 0;

            for (int j = 0; j < data.length; j++) {
                if (hasRequiredName(names[i], data[j])) {
                    splittedData = data[j].split(SPACE);

                    String date = splittedData[INDEX_ZERO];
                    String name = splittedData[INDEX_ONE];
                    String workHours = splittedData[INDEX_TWO];
                    String incomePerHour = splittedData[INDEX_THREE];

                    if (isNameAndDateMatch(names[i], name, date, dateFrom, dateTo)) {
                        salary += getSalary(workHours, incomePerHour);
                    }
                }
            }

            report.append(names[i])
                    .append(HYPHEN)
                    .append(salary)
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }

    private boolean hasRequiredName(String name, String stringData) {
        return stringData.contains(name);
    }

    private LocalDate getDate(String stringDate) {
        String[] splittedDate = stringDate.split("\\.");

        int day = Integer.parseInt(splittedDate[INDEX_ZERO]);
        int month = Integer.parseInt(splittedDate[INDEX_ONE]);
        int year = Integer.parseInt(splittedDate[INDEX_TWO]);

        return LocalDate.of(year, month, day);
    }

    private boolean isInDiapason(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return (date.isAfter(dateFrom) || date.isEqual(dateFrom))
                && (date.isBefore(dateTo) || date.isEqual(dateTo));
    }

    private boolean isNameAndDateMatch(String targetName, String currentName,
                                       String currentDate, String dateFrom, String dateTo) {
        return targetName.equals(currentName)
                && isInDiapason(getDate(currentDate), getDate(dateFrom), getDate(dateTo));
    }

    private int getSalary(String hours, String incomePerHour) {
        return Integer.parseInt(hours) * Integer.parseInt(incomePerHour);
    }
}
