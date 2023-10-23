import cv2

# Load the video file
video = cv2.VideoCapture('video.mp4')

# Create a background subtractor object
fgbg = cv2.createBackgroundSubtractorMOG2()

while True:
    # Read a frame from the video
    ret, frame = video.read()
    
    # Stop the loop if the video is over
    if not ret:
        break
    
    # Apply background subtraction to the frame
    fgmask = fgbg.apply(frame)
    
    # Apply thresholding to create a binary image
    thresh = cv2.threshold(fgmask, 200, 255, cv2.THRESH_BINARY)[1]
    
    # Find contours in the binary image
    contours, hierarchy = cv2.findContours(thresh, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    
    # Loop over the contours
    for cnt in contours:
        # Get the area of the contour
        area = cv2.contourArea(cnt)
        
        # Ignore small contours
        if area < 1000:
            continue
        
        # Draw a rectangle around the contour
        x, y, w, h = cv2.boundingRect(cnt)
        cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 0, 255), 2)
    
    # Display the video frame
    cv2.imshow('Traffic Detection', frame)
    
    # Exit if the user presses 'q'
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

# Release the video and close the window
video.release()
cv2.destroyAllWindows()
