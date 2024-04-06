using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CloudController : MonoBehaviour
{
    public float verticalSpeed;
    public float topVeticalSpeed;
    public float lowestVerticalSpeed;
    public float topBound;
    public float bottomBound;
    public float leftBound;
    public float rightBound;
    public float lowestDrift;
    public float highestDrift;
    public float driftAmount;

    // Start is called before the first frame update
    void Start()
    {
        ResetGameObject();
    }

    // Update is called once per frame
    void Update()
    {
        Move();
        CheckBounds();
    }

    void Move()
    {
        float moveX = driftAmount; // Horizontal movement
        float moveY = verticalSpeed; // Vertical movement

        // Update position
        transform.position -= new Vector3(moveX, moveY, 0);
    }


    void ResetGameObject()
    {
        // Generate a random X coordinate within the visible screen area
        float randomX = Random.Range(leftBound, rightBound);

        // Clamp the randomX within the specified leftBound and rightBound
        randomX = Mathf.Clamp(randomX, leftBound, rightBound);

        // Set the cloud's position
        transform.position = new Vector3(randomX, topBound, 0);

        // Set other parameters
        verticalSpeed = Random.Range(lowestVerticalSpeed, topVeticalSpeed);
        driftAmount = Random.Range(lowestDrift, highestDrift);
    }




    void CheckBounds()
    {
        if (transform.position.y <= bottomBound)
        {
            ResetGameObject();
        }
    }
}