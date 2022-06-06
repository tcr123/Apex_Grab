package com.example.loginassignment;

public class LocationKey {
    public static String PointOrder(int c)
    {
        switch (c)
        {
            case 0:
                return "SLUMMLAKES";
            case 1:
                return "CONTAINEMENT";
            case 2:
                return "RUNOFF";
            case 3:
                return "THE PIT";
            case 4:
                return "AIRBASE";
            case 5:
                return "BUNKER";
            case 6:
                return "THUNDERDOME";
            case 7:
                return "SKULL TOWN";
            case 8:
                return "MARKET";
            case 9:
                return "WATER TREATMENT";
            case 10:
                return "REPULSOR";
            case 11:
                return "THE CAGE";
            case 12:
                return "ARTILLERY";
            case 13:
                return "RELAY";
            case 14:
                return "WETLANDS";
            case 15:
                return "SWAMPS";
            case 16:
                return "HYDRO DAM";
        }
        return "";
    }

    public static int LocationNum(String c)
    {
        switch (c)
        {
            case "SLUMMLAKES":
                return 0;
            case "CONTAINEMENT":
                return 1;
            case "RUNOFF":
                return 2;
            case "THE PIT":
                return 3;
            case "AIRBASE":
                return 4;
            case "BUNKER":
                return 5;
            case "THUNDERDOME":
                return 6;
            case "SKULL TOWN":
                return 7;
            case "MARKET":
                return 8;
            case "WATER TREATMENT":
                return 9;
            case "REPULSOR":
                return 10;
            case "THE CAGE":
                return 11;
            case "ARTILLERY":
                return 12;
            case "RELAY":
                return 13;
            case "WETLANDS":
                return 14;
            case "SWAMPS":
                return 15;
            case "HYDRO DAM":
                return 16;
        }
        return -1;
    }

    public static Location Coordinate(String ch)
    {
        switch (ch)
        {
            case "SLUMMLAKES":
                return new Location(120,191);
            case "CONTAINEMENT":
                return new Location(351,219);
            case "RUNOFF":
                return new Location(107,310);
            case "THE PIT":
                return new Location(189,260);
            case "AIRBASE":
                return new Location(121,433);
            case "BUNKER":
                return new Location(237,357);
            case "THUNDERDOME":
                return new Location(168,661);
            case "SKULL TOWN":
                return new Location(252,582);
            case "MARKET":
                return new Location(374,536);
            case "WATER TREATMENT":
                return new Location(444,715);
            case "REPULSOR":
                return new Location(620,580);
            case "THE CAGE":
                return new Location(529,409);
            case "ARTILLERY":
                return new Location(427,111);
            case "RELAY":
                return new Location(669,162);
            case "WETLANDS":
                return new Location(597,256);
            case "SWAMPS":
                return new Location(736,380);
            case "HYDRO DAM":
                return new Location(630,476);
        }
        return new Location(0, 0);
    }
}
