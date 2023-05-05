import java.util.*;
class Solution {
        long answer = 0;
        int deliveriesIdx = 0;
        int pickupsIdx = 0;
        int[] globalDeliveries;
        int[] globalPickups;

        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            globalPickups = pickups;
            globalDeliveries = deliveries;

            for (int i = deliveries.length - 1; i >= 0; i--) {
                if (deliveries[i] > 0) {
                    deliveriesIdx = i;
                    break;
                }
            }

            for (int i = pickups.length - 1; i >= 0; i--) {
                if (pickups[i] > 0) {
                    pickupsIdx = i;
                    break;
                }
            }

            while (true) {
                if (deliveriesIdx == 0 && pickupsIdx == 0 && globalDeliveries[0] == 0 && globalPickups[0] == 0) {
                    break;
                }
                check(cap);
            }


            return answer;
        }

        public void check(int cap) {
            int deliveriesCap = cap;
            int pickupsCap = cap;
            int dIdx = deliveriesIdx + 1;
            int pIdx = pickupsIdx + 1;

            for (int i = deliveriesIdx; i >= 0; i--) {
                if (i == 0) {
                    deliveriesIdx = 0;
                }

                if (globalDeliveries[i] == 0) {
                    continue;
                }

                if (deliveriesCap == 0) {
                    deliveriesIdx = i;
                    break;
                }

                if (globalDeliveries[i] > deliveriesCap) {
                    globalDeliveries[i]-= deliveriesCap;
                    deliveriesIdx = i;
                    break;
                } else if(globalDeliveries[i] == deliveriesCap) {
                    globalDeliveries[i]-= deliveriesCap;
                    deliveriesCap = 0;
                }else {
                    deliveriesCap -= globalDeliveries[i];
                    globalDeliveries[i] = 0;
                }
            }

            for (int i = pickupsIdx; i >= 0; i--) {
                if (i == 0) {
                    pickupsIdx = 0;
                }

                if (globalPickups[i] == 0) {
                    continue;
                }

                if (pickupsCap == 0) {
                    pickupsIdx = i;
                    break;
                }

                if (globalPickups[i] > pickupsCap) {
                    globalPickups[i]-= pickupsCap;
                    pickupsIdx = i;
                    break;
                } else if (globalPickups[i] == pickupsCap) {
                    globalPickups[i]-= pickupsCap;
                    pickupsCap = 0;
                }else {
                    pickupsCap -= globalPickups[i];
                    globalPickups[i] = 0;
                }


            }

            answer += (Math.max(dIdx, pIdx) * 2);
        }
    }
