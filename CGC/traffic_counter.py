import cv2
import numpy as np

cap = cv2.VideoCapture('video.mp4')
fgbg = cv2.createBackgroundSubtractorMOG2()
min_contour_area = 250
column_count1 = 0
column_count2 = 0
frame_width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
frame_height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
print(frame_width)
frameid = 1
while cap.isOpened():
    ret, frame = cap.read()
    if not ret:
        break
    fgmask = fgbg.apply(frame)
    kernel = np.ones((3, 3), np.uint8)
    fgmask = cv2.erode(fgmask, kernel, iterations=1)
    fgmask = cv2.dilate(fgmask, kernel, iterations=1)
    contours, hierarchy = cv2.findContours(fgmask, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    for contour in contours:
        area = cv2.contourArea(contour)
        if area > min_contour_area:
            x, y, w, h = cv2.boundingRect(contour)

            center = (int((2 * x + w) / 2), int((2 * y + h) / 2))
            if center[0] < 1300 and 100 > center[1] > 70:
                column_count1 += 1

                print(x, y, frameid)
            elif 100 > center[1] > 70:
                column_count2 += 1

                print(x, y, frameid)
            frameid += 1

            if center[0] < 1300:
                cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 0, 255), 2)
            else:
                cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 255, 0), 2)



    cv2.putText(frame, 'Left count:  ' + str(column_count1), (20, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 255, 255),
                2)
    cv2.putText(frame, 'Right count:  ' + str(column_count1), (1100, 30), cv2.FONT_HERSHEY_SIMPLEX, 1,
                (255, 255, 255),
                2)
    cv2.putText(frame, "Chandrashekar S20200010154", (20,100), cv2.FONT_HERSHEY_SIMPLEX, 2, (0, 255, 0), 2)
    cv2.imshow('Traffic Counter', frame)

    if cv2.waitKey(1) == ord('q'):
        break
cap.release()
cv2.destroyAllWindows()