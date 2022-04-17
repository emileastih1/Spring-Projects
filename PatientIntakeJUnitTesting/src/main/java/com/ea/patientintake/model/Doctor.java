package com.ea.patientintake.model;

public enum Doctor {
   avery("Ralph Avery"),
   johnson("Beth Johnson"),
   murphy("Pat Murpy");

   private String name;

   Doctor(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
