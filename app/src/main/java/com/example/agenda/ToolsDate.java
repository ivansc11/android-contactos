package com.example.agenda;

public class ToolsDate {
    public static String getBirthdaySave(int year, int month, int day){
        String birthdayToSave;
        birthdayToSave=year+"-";
        if(month+1<10){
            birthdayToSave+="0";
        }
        birthdayToSave+=((month+1)+"-");
        if(day<10){
            birthdayToSave+="0";
        }
        birthdayToSave+=day;

        return birthdayToSave;

    }

    public static String stringToDate(String date){
        String day,month,year;
        day=date.substring(8);
        month=date.substring(5,7);
        year= date.substring(0,4);
        return day+"/"+month+"/"+year;
    }

    public static String pickerToString(int day, int month, int year){
        String dateToPickerShow="";
        if(day<10){
            dateToPickerShow+="0";
        }
        dateToPickerShow+=(day+"/");
        if(month+1<10){
            dateToPickerShow+="0";
        }
        dateToPickerShow+=((month+1)+"/");
        dateToPickerShow+=year;
        return dateToPickerShow;
    }

}
